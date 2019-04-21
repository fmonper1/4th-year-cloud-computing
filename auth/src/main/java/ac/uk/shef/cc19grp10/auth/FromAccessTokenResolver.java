package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.AccessToken;
import ac.uk.shef.cc19grp10.auth.data.AccessTokenRepository;
import ac.uk.shef.cc19grp10.auth.data.Authorisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <Doc here>
 * <p>
 * Created on 18/04/2019.
 */
@Component
public class FromAccessTokenResolver implements HandlerMethodArgumentResolver {

	static final Pattern BEARER_PATTERN = Pattern.compile("Bearer (.*)");

	Logger logger = LoggerFactory.getLogger(FromAccessTokenResolver.class);

	@Autowired
	private AccessTokenRepository accessTokenRepo;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(FromAccessToken.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//get the parameter
		String accessTokenEncoded = webRequest.getParameter("access_token");
		logger.info("accessTokenEncoded: {}",accessTokenEncoded);
		//if that's missing
		if (accessTokenEncoded == null){
			logger.info("accessTokenEncoded is null");
			//get the header
			String authHeader = webRequest.getHeader("Authorization");
			logger.info("authHeader: {}",authHeader);
			if(authHeader != null) {
				//and extract the relevant part
				Matcher matcher = BEARER_PATTERN.matcher(authHeader);
				if (matcher.matches()) {
					accessTokenEncoded = matcher.group(1);
				}

			}
		}
		logger.info("accessTokenEncoded: {}",accessTokenEncoded);
		//if we still don't have it we can't find it
		if (accessTokenEncoded == null) {
			logger.info("accessTokenEncoded is null");
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		AccessToken accessToken = accessTokenRepo.findByAccessToken(Base64.getUrlDecoder().decode(accessTokenEncoded));
		if (accessToken == null) {
			logger.info("accessToken is null");
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		Authorisation auth = accessToken.getAuthorisation();
		if (auth == null) {
			logger.info("auth is null");
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		return auth;
	}
}
