package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.Normalizer;

/**
 * <Doc here>
 * <p>
 * Created on 12/04/2019.
 */
@Controller
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	ApplicationRepository appRepo;

	Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@GetMapping
	public ModelAndView viewApplication(
			@SessionAttribute User user
	)
	{
		return new ModelAndView("developer/index","application",user.getApplication());
	}

	@GetMapping("/create")
	public ModelAndView createApplication(
			@ModelAttribute CreateApplicationForm createApplicationForm
	)
	{
		return new ModelAndView("developer/create");
	}

	@PostMapping("/create")
	public ModelAndView createApplication(
			@ModelAttribute @Valid CreateApplicationForm createApplicationForm,
			@SessionAttribute User user,
			HttpServletRequest request,
			BindingResult bindingResult
	)
	{
		if(user.hasApplication()){
			//It's not actually that the name is in use, but the client id, but they don't need to know that.
			bindingResult.addError(new ObjectError(
					"createApplicationForm",
					"You already have an application. Please edit that one."
			));
			return new ModelAndView("developer/create");
		}
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/create");
		}
		String redirectUri = createApplicationForm.redirectUri;
		String applicationName = createApplicationForm.applicationName;
		String clientId = nameToClientId(applicationName);
		Application existingApplication = appRepo.findByClientId(clientId);
		if(existingApplication != null){
			//It's not actually that the name is in use, but the client id, but they don't need to know that.
			bindingResult.addError(new FieldError(
					"createApplicationForm",
					"applicationName",
					"Another application is already using that name"
			));
			return new ModelAndView("developer/create");
		}
		Application app = appRepo.save(new Application(applicationName,clientId, user, redirectUri));
		return new ModelAndView(new RedirectView("/developer"));
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

	public class CreateApplicationForm{
		@Size(min=5)
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
	}
}
