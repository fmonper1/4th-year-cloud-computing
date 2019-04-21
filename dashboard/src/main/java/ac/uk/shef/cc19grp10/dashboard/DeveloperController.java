package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.dashboard.data.Application;
import ac.uk.shef.cc19grp10.dashboard.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.dashboard.data.AuthApplication;
import ac.uk.shef.cc19grp10.dashboard.data.User;
import ac.uk.shef.cc19grp10.dashboard.services.ApplicationManagementService;
import ac.uk.shef.cc19grp10.utils.validation.FieldsMatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

		//TODO: handle ApiError properly
		AuthApplication authApp = appManagement.getAuthApplication(user);
		Application app = user.getApplication();

		logger.info("app from user: {}",app);
		
		app = appRepo.findByOwner(user);

		logger.info("app from repo: {}",app);

		return new ModelAndView("developer/index")
				.addObject("app",app)
				.addObject("authApp",authApp);
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

	@PostMapping("/auth/create")
	public ModelAndView createAuthApplication(
			@ModelAttribute @Valid CreateAuthApplicationForm createApplicationForm,
			BindingResult bindingResult,
			@SessionAttribute("user") User user
	)
	{
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/auth/create");
		}
		try {
			appManagement.createAuthApplication(
					createApplicationForm.applicationName,
					createApplicationForm.redirectUri,
					user
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
		if(bindingResult.hasErrors()){
			return new ModelAndView("developer/db/create");
		}
		appManagement.createDbApplication(
				createApplicationForm.applicationName,
				createApplicationForm.databasePassword,
				user
		);
		return new ModelAndView(new RedirectView("/developer"));
	}

	@FieldsMatch({"databasePassword","confirmDatabasePassword"})
	public class CreateAuthApplicationForm{
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


	@FieldsMatch({"databasePassword","confirmDatabasePassword"})
	public class CreateDbApplicationForm{
		@Size(min=5)
		String applicationName;
		@Size(min=3)
		String databasePassword;
		@Size(min=3)
		String confirmDatabasePassword;

		public String getApplicationName() {
			return applicationName;
		}

		public void setApplicationName(String applicationName) {
			this.applicationName = applicationName;
		}

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
}
