package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
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

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	HashingStrategy hashingStrategy;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@GetMapping
	public ModelAndView login(
			@ModelAttribute LoginForm loginForm,
			@RequestParam("client_id") String clientId)
	{
		logger.info("Login get query");
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return new ModelAndView("misconfigured","reason","Bad client_id");
		}

		return new ModelAndView("login");
	}

	@PostMapping
	public ModelAndView postLogin(
			@ModelAttribute @Valid LoginForm loginForm,
			BindingResult bindingResult,
			@RequestParam("client_id") String clientId,
			HttpServletRequest request)
	{
		logger.info("Login post query");
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return new ModelAndView("misconfigured","reason","Bad client_id");
		}

		if(bindingResult.hasErrors()){
			return new ModelAndView("login");
		}

		User user = userRepo.findByName(loginForm.username);

		if(user!=null&&user.passwordMatches(loginForm.password,hashingStrategy)){

			RedirectView redirectView = new RedirectView("auth");
			redirectView.setPropagateQueryParams(true);
			logger.info("user being set to: {} \n",user.toString());

			request.getSession().setAttribute("user", user);
			return new ModelAndView(redirectView);
		}else{
			logger.info("Incorrect Username or password");
			bindingResult.addError(new ObjectError("signupForm","Incorrect Username or password"));
			return new ModelAndView("login");
		}
	}

	public class LoginForm {
		@NotBlank
		String username;
		@Size(min = 3, max=255)
		String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}
