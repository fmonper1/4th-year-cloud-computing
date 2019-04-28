package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
@Component
public class LoadUserInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(LoadUserInterceptor.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean preHandle (HttpServletRequest request,
							  HttpServletResponse response,
							  Object handler) {
		Long userId = (Long) request.getSession().getAttribute("userId");
		if(userId != null){
			request.getSession().setAttribute("user",userRepo.findById(userId).orElse(null));
		}

		return true;
	}
}