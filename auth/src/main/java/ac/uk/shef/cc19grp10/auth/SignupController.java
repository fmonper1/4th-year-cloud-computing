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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
@Controller
public class SignupController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	HashingStrategy hashingStrategy;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@RequestMapping( value= "/signup", method = RequestMethod.GET)
	public ModelAndView signup()
	{
		return new ModelAndView("signup","signupForm", new SignupForm());
	}

	@RequestMapping( value= "/signup", method = RequestMethod.POST)
	public ModelAndView postSignup(
			@Valid SignupForm signupForm,
			@RequestParam("client_id") String clientId,
			HttpServletRequest request)
	{
		logger.info("Login post query");
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return new ModelAndView("misconfigured","reason","Bad client_id");
		}

		User existingUser = userRepo.findByName(signupForm.username);

		if(existingUser!=null){
			Map<String,Object> args = new HashMap<>();
			args.put("signupForm",signupForm);
			args.put("error","Username is not available");
			return new ModelAndView("signup", args);
		}

		if(!signupForm.confirmPassword.equals(signupForm.password)){
			Map<String,Object> args = new HashMap<>();
			args.put("signupForm",signupForm);
			args.put("error","Passwords do not match");
			return new ModelAndView("signup", args);
		}

		User user = userRepo.save(new User(signupForm.username,signupForm.password,hashingStrategy));
		request.getSession().setAttribute("user", user);

		RedirectView redirectView = new RedirectView("/");
		redirectView.setPropagateQueryParams(true);
		return new ModelAndView(redirectView);
	}

	public class SignupForm {
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

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		String username;
		String password;
		String confirmPassword;
	}
}
