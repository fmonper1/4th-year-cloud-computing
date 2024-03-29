package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle (HttpServletRequest request,
							  HttpServletResponse response,
							  Object handler) throws Exception {
		User user = (User) request.getSession()
				.getAttribute("user");
		if (user == null) {
			String queryString = "";
			if(request.getQueryString()!=null){
				queryString = "?"+request.getQueryString();
			}
			String redirectTo = (request.getRequestURI() + queryString).substring(request.getContextPath().length());
			request.getSession().setAttribute("loginRedirect",redirectTo);
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}

		return true;
	}
}