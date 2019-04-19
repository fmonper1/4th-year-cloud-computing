package ac.uk.shef.cc19grp10.utils.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Value("${auth.redirect_uri}")
	private String redirectUri;
	@Value("${auth.client_id}")
	private String clientId;
	@Value("${auth.auth_servlet_url:http://143.167.9.214:8080/auth}")
	private String authServletBase;
	private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession()
				.getAttribute("user");
		logger.info("uri: {}",request.getRequestURI());
		logger.info("user: {}",user);
		if (user == null) {
			String state = request.getRequestURI();
			HashMap<String,String> queryParams = new HashMap<>();
			queryParams.put("state",state);
			queryParams.put("redirect_uri", URLEncoder.encode(redirectUri,"UTF-8"));
			queryParams.put("client_id",clientId);
			String queryString = queryParams.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
			response.sendRedirect(authServletBase+"/auth?"+queryString);
			return false;
		}
		return true;
	}
}
