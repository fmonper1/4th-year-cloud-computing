package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import ac.uk.shef.cc19grp10.auth.security.PBKDF2HashingStrategy;
import org.hibernate.hql.internal.ast.SqlASTFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Controller
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
    HashingStrategy hashingStrategy;

    @Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@RequestMapping( value= "/", method = RequestMethod.GET)
	public ModelAndView auth(
			@RequestParam("client_id") String clientId,
			@RequestParam("redirect_uri") String redirectUri,
			@RequestParam(name = "state", required=false) String state,
			@SessionAttribute(name="user") User user)
	{
		logger.info("User: {}", user);
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return new ModelAndView("misconfigured","reason","Bad client_id");
		}

		if (user == null){
			RedirectView redirectView = new RedirectView("login");
			redirectView.setPropagateQueryParams(true);
			return new ModelAndView(redirectView);
		}

		return new ModelAndView("authorise","app",app);
	}

	@RequestMapping( value= "/", method = RequestMethod.POST)
	public ModelAndView postAuth(
			@Valid AuthForm authForm,
			@RequestParam("client_id") String clientId,
			@RequestParam("redirect_uri") String redirect,
			@RequestParam(name = "state", required=false) String state,
			@SessionAttribute(name="user") User user) throws URISyntaxException {
		logger.info("User: {}", user);
		Application app = appRepo.findByClientId(clientId);
		if (app == null){
			return new ModelAndView("misconfigured","reason","Bad client_id");
		}

		logger.info("authForm: {}", authForm.accept);
		String authParams;
		if(authForm.accept!=null&&authForm.accept.equals("yes")){
			String auth_code = "success";//TODO: use a real auth code
			authParams = "code="+auth_code;
			if (state != null){
				authParams += "&state=" + state;
			}
		}else if(authForm.accept!=null&&authForm.accept.equals("no")){
			authParams = "error=access_denied";
		}else{
			return new ModelAndView("authorise","error","You must select either yes or no.");
		}

		URI redirectUri = new URI(redirect);
		if (redirectUri.getQuery()!=null&&redirectUri.getQuery().isEmpty()){
			redirect += "&";
		}else{
			redirect += "?";
		}
		redirect += authParams;
		return new ModelAndView("redirect:"+redirect);
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
