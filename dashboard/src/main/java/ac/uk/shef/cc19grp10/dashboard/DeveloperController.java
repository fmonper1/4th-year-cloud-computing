package ac.uk.shef.cc19grp10.dashboard;

import ac.uk.shef.cc19grp10.dashboard.data.*;
import ac.uk.shef.cc19grp10.dashboard.services.ApplicationManagementService;
import ac.uk.shef.cc19grp10.utils.validation.FieldsMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <Doc here>
 * <p>
 * Created on 12/04/2019.
 */
@Controller
@RequestMapping("/developer")
public class DeveloperController {

	@Autowired
	private ApplicationManagementService appManagement;

	private Logger logger = LoggerFactory.getLogger(DeveloperController.class);

	@Autowired
	private ApplicationRepository appRepo;

	@GetMapping
	public ModelAndView viewApplication(
			@SessionAttribute("user") User user
	) throws ApplicationManagementService.ApiError {

		Application app = appRepo.findByOwner(user);
		AuthApplication authApp = null;
		DbApplication dbApp = null;
		if(app != null) {
			dbApp = app.getDbApplication();
			//TODO: handle ApiError properly
			authApp = appManagement.getAuthApplication(user);
		}

		return new ModelAndView("developer/index")
				.addObject("app",app)
				.addObject("authApp",authApp)
				.addObject("dbApp",dbApp);
	}

	@GetMapping("/upload")
	public ModelAndView deployApplication(
			@ModelAttribute CreateDeploymentForm createDeploymentForm
	)
	{
		return new ModelAndView("developer/upload");
	}

	@GetMapping("/create")
	public ModelAndView createApplication(
			@ModelAttribute CreateApplicationForm createApplicationForm
	)
	{
		return new ModelAndView("developer/create");
	}

	@GetMapping("/auth/create")
	public ModelAndView createApplication(
			@ModelAttribute CreateAuthApplicationForm createApplicationForm
	)
	{
		return new ModelAndView("developer/auth/create");
	}

	@GetMapping("/db/create")
	public ModelAndView createApplication(
			@ModelAttribute CreateDbApplicationForm createApplicationForm
	)
	{
		return new ModelAndView("developer/db/create");
	}

	@PostMapping("/upload")
	public ModelAndView deployApplication(
			@ModelAttribute @Valid CreateDeploymentForm createDeploymentForm,
			BindingResult bindingResult,
			@SessionAttribute("user") User user
	)
	{
		Application application = user.getApplication();
		if(application == null){
			bindingResult.addError(new ObjectError("createDeploymentForm","You must create the parent application first"));
		}
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/upload");
		}
		try {
			appManagement.createDeployment(
					createDeploymentForm.warFile,
					application
			);
		} catch (ApplicationManagementService.ApiError apiError) {
			bindingResult.addError(new ObjectError(
					"createDeploymentForm",
					"There was an error with the deployment api. Please report this to a member of team 1."
			));
			return new ModelAndView("developer/upload");
		}
		return new ModelAndView(new RedirectView("/developer"));
	}

	@PostMapping("/create")
	public ModelAndView createApplication(
			@ModelAttribute @Valid CreateApplicationForm createApplicationForm,
			BindingResult bindingResult,
			@SessionAttribute("user") User user
	)
	{
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/create");
		}
		appManagement.createApplication(
				createApplicationForm.applicationName,
				createApplicationForm.description,
				user
		);
		return new ModelAndView(new RedirectView("/developer"));
	}

	@PostMapping("/auth/create")
	public ModelAndView createAuthApplication(
			@ModelAttribute @Valid CreateAuthApplicationForm createApplicationForm,
			BindingResult bindingResult,
			@SessionAttribute("user") User user
	)
	{
		Application application = user.getApplication();
		if(application == null){
			bindingResult.addError(new ObjectError("createApplicationForm","You must create the parent application first"));
		}
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/auth/create");
		}
		try {
			appManagement.createAuthApplication(
					createApplicationForm.redirectUri,
					user,
					application
			);
		} catch (ApplicationManagementService.ApiError apiError) {
			bindingResult.addError(new ObjectError(
					"createApplicationForm",
					"There was an error with the authorization service application creation api. Please report this to a member of team 1."
			));
			return new ModelAndView("developer/auth/create");
		}
		return new ModelAndView(new RedirectView("/developer"));
	}

	@PostMapping("/db/create")
	public ModelAndView createDbApplication(
			@ModelAttribute @Valid CreateDbApplicationForm createApplicationForm,
			BindingResult bindingResult,
			@SessionAttribute("user") User user
	)
	{
		Application application = user.getApplication();
		if(application == null){
			bindingResult.addError(new ObjectError("createApplicationForm","You must create the parent application first"));
		}
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/db/create");
		}
		appManagement.createDbApplication(
				createApplicationForm.databasePassword,
				application
		);
		return new ModelAndView(new RedirectView("/developer"));
	}

	public class CreateAuthApplicationForm{
		@NotBlank
		String redirectUri;

		public String getRedirectUri() {
			return redirectUri;
		}

		public void setRedirectUri(String redirectUri) {
			this.redirectUri = redirectUri;
		}
	}


	@FieldsMatch({"databasePassword","confirmDatabasePassword"})
	public class CreateDbApplicationForm{
		@Size(min=3)
		String databasePassword;
		@Size(min=3)
		String confirmDatabasePassword;

		public String getDatabasePassword() {
			return databasePassword;
		}

		public void setDatabasePassword(String databasePassword) {
			this.databasePassword = databasePassword;
		}

		public String getConfirmDatabasePassword() {
			return confirmDatabasePassword;
		}

		public void setConfirmDatabasePassword(String confirmDatabasePassword) {
			this.confirmDatabasePassword = confirmDatabasePassword;
		}
	}

	public class CreateApplicationForm {
		@Size(min=5,max=28)
		String applicationName;

		@NotBlank
		String description;

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	public class CreateDeploymentForm {
		@NotNull
		MultipartFile warFile;

		public MultipartFile getWarFile() {
			return warFile;
		}

		public void setWarFile(MultipartFile warFile) {
			this.warFile = warFile;
		}
	}
}
