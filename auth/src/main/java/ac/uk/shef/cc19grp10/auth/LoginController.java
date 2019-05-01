package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Controller
@RequestMapping("/login")
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private static final String DEFAULT_SUCCESS_REDIRECT = "/auth";
	private static final String LOGIN_REDIRECT_ATTR_NAME = "loginRedirect";

	@Autowired
	HashingStrategy hashingStrategy;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@GetMapping
	public ModelAndView login(
			@ModelAttribute LoginForm loginForm,
			@SessionAttribute(required = false) User user,
			HttpServletRequest request
	)
	{
		logger.info("Login get query");

		if (user != null) {
			logger.info("User {} already signed in, redirecting...", user);
			return handleRedirect(request);
		}

		return new ModelAndView("login");
	}

	@PostMapping
	public ModelAndView postLogin(
			@ModelAttribute @Valid LoginForm loginForm,
			BindingResult bindingResult,
			HttpServletRequest request
	)
	{
		logger.info("Login post query");
		if(bindingResult.hasErrors()){
			return new ModelAndView("login");
		}

		User user = userRepo.findByName(loginForm.username);

		if (user != null && user.passwordMatches(loginForm.password, hashingStrategy)) {
			request.getSession().setAttribute("userId", user.getId());
			return handleRedirect(request);
		} else {
			logger.info("Incorrect Username or password");
			bindingResult.addError(new ObjectError("signupForm", "Incorrect Username or password"));
			return new ModelAndView("login");
		}
	}

	private ModelAndView handleRedirect(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String loginRedirect = (String) session.getAttribute(LOGIN_REDIRECT_ATTR_NAME);

		if (loginRedirect == null) {
			loginRedirect = DEFAULT_SUCCESS_REDIRECT;
		}

		request.getSession().removeAttribute(LOGIN_REDIRECT_ATTR_NAME); // Remove if set

		return new ModelAndView(new RedirectView(loginRedirect,true));
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
