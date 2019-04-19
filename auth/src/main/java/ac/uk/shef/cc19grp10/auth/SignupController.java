package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import ac.uk.shef.cc19grp10.utils.validation.FieldsMatch;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RequestMapping("/signup")
public class SignupController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	HashingStrategy hashingStrategy;

	@Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@GetMapping
	public ModelAndView signup()
	{
		return new ModelAndView("signup","signupForm", new SignupForm());
	}

	@PostMapping
	public ModelAndView postSignup(
			@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult bindingResult,
			HttpServletRequest request,
			@SessionAttribute(required = false) String loginRedirect) throws JsonProcessingException {
		logger.info("Signup post query");
		logger.info(new ObjectMapper().writeValueAsString(signupForm));

		User existingUser = userRepo.findByName(signupForm.username);

		if(existingUser!=null){
			bindingResult.addError(new FieldError("signupForm","username","Username is not available"));
			return new ModelAndView("signup");
		}

		if(bindingResult.hasErrors()){
			return new ModelAndView("signup");
		}

		User user = userRepo.save(new User(signupForm.username,signupForm.password,hashingStrategy));
		request.getSession().setAttribute("userId", user.getId());

		//redirect to auth by default
		loginRedirect = loginRedirect==null?"/auth":loginRedirect;
		//remove redirect if set
		request.getSession().removeAttribute("loginRedirect");

		RedirectView redirectView = new RedirectView(loginRedirect);
		redirectView.setPropagateQueryParams(true);
		return new ModelAndView(redirectView);
	}

	@FieldsMatch({"password","confirmPassword"})
	public class SignupForm {
		@NotBlank
		String username;
		@Size(min = 3, max=255)
		String password;
		@Size(min = 3, max=255)
		String confirmPassword;

		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
	}
}
