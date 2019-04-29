package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
    HashingStrategy hashingStrategy;

    @Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	AuthorisationRepository authRepo;

	@GetMapping
	public ModelAndView auth(
			@RequestParam("client_id") String clientId,
			@RequestParam("redirect_uri") String redirect,
			@RequestParam(name = "state", required=false) String state,
			@SessionAttribute(name="user") User user) throws URISyntaxException {
		logger.info("User: {}", user);
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return misconfiguredClientId();
		}

		Authorisation authorisation = authRepo.findByUserAndApplication(user,app);
		if (authorisation != null){
			return completeAuthorisationSuccess(user,app,redirect,state);
		}

		return new ModelAndView("authorise","app",app);
	}

	@PostMapping
	public ModelAndView postAuth(
			@Valid AuthForm authForm,
			@RequestParam("client_id") String clientId,
			@RequestParam("redirect_uri") String redirect,
			@RequestParam(name = "state", required=false) String state,
			@SessionAttribute(name="user") User user) throws URISyntaxException {
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return misconfiguredClientId();
		}

		if(authForm.accept!=null&&authForm.accept.equals("yes")){
			return completeAuthorisationSuccess(user,app,redirect,state);
		}else if(authForm.accept!=null&&authForm.accept.equals("no")){
			return completeAuthorisationDenied(redirect,state);
		}else{
			return new ModelAndView("authorise","error","You must select either yes or no.");
		}
	}

	private ModelAndView misconfiguredClientId() throws URISyntaxException {
		return new ModelAndView("misconfigured","reason","Bad client_id");
	}

	private ModelAndView completeAuthorisationDenied(String redirect, String state) throws URISyntaxException {
		String authParams = "error=access_denied";
		if (state != null){
			authParams += "&state=" + state;
		}
		return completeAuthorisation(redirect,authParams);
	}

	private ModelAndView completeAuthorisationSuccess(User user, Application application, String redirect, String state) throws URISyntaxException {
		Authorisation authorisation = authRepo.save(new Authorisation(user,application));
		return completeAuthorisationSuccess(authorisation,redirect,state);
	}
	private ModelAndView completeAuthorisationSuccess(Authorisation authorisation, String redirect, String state) throws URISyntaxException {
		String authParams = "code="+authorisation.getAuthCodeEncoded();;
		if (state != null){
			authParams += "&state=" + state;
		}
		return completeAuthorisation(redirect,authParams);
	}

	private ModelAndView completeAuthorisation(String redirect,String authParams) throws URISyntaxException {
		URI redirectUri = new URI(redirect);
		if (redirectUri.getQuery()==null||redirectUri.getQuery().isEmpty()){
			redirect += "?";
		}else{
			redirect += "&";
		}
		redirect += authParams;
		//test if the uri has a scheme
		if (!redirect.matches("([a-zA-z0-9+\\-.])*://.*")){
			//if not use the empty scheme, which means use the current scheme
			redirect = "://"+redirect;
		}
		return new ModelAndView(new RedirectView(redirect,false));
	}

	public class AuthForm{
		String accept;

		public String getAccept() {
			return accept;
		}

		public void setAccept(String accept) {
			this.accept = accept;
		}
	}
}
