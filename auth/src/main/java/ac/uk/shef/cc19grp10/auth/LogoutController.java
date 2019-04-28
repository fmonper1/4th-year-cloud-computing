package ac.uk.shef.cc19grp10.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	private final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@RequestMapping
	public ModelAndView logout(HttpServletRequest request)
	{
		logger.info("Logging out, destroying session.");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return new ModelAndView("redirect:/login");
	}

}
