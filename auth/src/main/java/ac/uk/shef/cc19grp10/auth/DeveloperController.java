package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import ac.uk.shef.cc19grp10.auth.services.ApplicationManagement;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * <Doc here>
 * <p>
 * Created on 12/04/2019.
 */
@RestController
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	ApplicationManagement appManagement;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@GetMapping
	public Application viewApplication(
			@FromAccessToken Authorisation auth
	)
	{
		User user = auth.getUser();
		Application app = user.getApplication();
		logger.info("Returning app {} for user {}",app,user);
		return app;
	}

	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> createApplication(
			@RequestBody @Valid CreateApplicationForm createApplicationForm,
			BindingResult bindingResult,
			@FromAccessToken Authorisation auth
	)
	{
		User user = auth.getUser();
		if(user.hasApplication()){
			//This is mostly just laziness to be honest
			bindingResult.addError(new ObjectError(
					"createApplicationForm",
					"You already have an application. Please edit that one."
			));
		}
		if(bindingResult.hasErrors()){
			logger.error("Got bad createApplicationForm: {}",createApplicationForm);
			return ResponseEntity.badRequest().body(new JsonErrors(bindingResult));
		}
		try {
			appManagement.createApplication(
					createApplicationForm.redirectUri,
					createApplicationForm.applicationName,
					user
			);
		} catch (ApplicationManagement.ApplicationExistsError applicationExistsError) {
			bindingResult.addError(new FieldError(
					"createApplicationForm",
					"applicationName",
					"An application with that name already exists."
			));
			return ResponseEntity.badRequest().body(new JsonErrors(bindingResult));
		}
		return ResponseEntity.ok(user.getApplication());
	}

	/**
	 * Create a normalised, url-safe client id slug.
	 *
	 * Replaces non-ascii characters with a close equivalent,
	 * and removes non-alphanumeric characters
	 */
	private String nameToClientId(String applicationName) {
		//unicode normalise
		return Normalizer.normalize(applicationName, Normalizer.Form.NFD)
				//replace space with dash
				.replaceAll("\\p{Space}","-")
				//remove non alphanumeric/dash characters
				.replaceAll("[^\\p{Alnum}-]", "")
				//and lowercase it
				.toLowerCase();
	}

	private static class JsonError {
		@JsonProperty
		String object;
		@JsonProperty
		String field;
		@JsonProperty
		String error;
		JsonError(ObjectError error) {
			this.object = error.getObjectName();
			this.error = error.getDefaultMessage();
			if( error instanceof FieldError){
				this.field = ((FieldError) error).getField();
			}
		}
	}

	private static class JsonErrors {
		@JsonProperty
		List<JsonError> errors = new ArrayList<>();
		JsonErrors(BindingResult bindingResult) {
			for (ObjectError error : bindingResult.getAllErrors()){
				errors.add(new JsonError(error));
			}
		}
	}

	public static class CreateApplicationForm{
		@Size(min=5,max=28)
		String applicationName;
		@NotBlank
		String redirectUri;

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		public String getRedirectUri() {
			return redirectUri;
		}

		public void setRedirectUri(String redirectUri) {
			this.redirectUri = redirectUri;
		}

		@Override
		public String toString() {
			return "CreateApplicationForm{applicationName="+applicationName+",redirectUri="+redirectUri+"}";
		}
	}
}
