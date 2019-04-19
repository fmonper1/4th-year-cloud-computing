package ac.uk.shef.cc19grp10.utils.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
@Component
@Controller
@RequestMapping("/auth/callback")
@ConditionalOnProperty("auth")
public class AuthCallbackController {

	private final RestTemplate restTemplate;
	@Value("${auth.redirect_uri}")
	private String redirectUri;
	@Value("${auth.client_id}")
	private String clientId;
	@Value("${auth.client_secret}")
	private String clientSecret;
	@Value("${auth.auth_servlet_url:http://143.167.9.214:8080/auth}")
	private String authServletBase;

	@Autowired
	private UserFactory userFactory;
	private Logger logger = LoggerFactory.getLogger(AuthCallbackController.class);

	public AuthCallbackController(){
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter(), new FormHttpMessageConverter()));
	}

	@GetMapping(params = "!error")
	public String handleSuccess(@RequestParam("code") String authCode, @RequestParam("state") String state, HttpServletRequest request){
		logger.info("handleSuccess");
		logger.info("authServletBase: {}", authServletBase);
		TokenResponse tokenResponse;
		{
			LinkedMultiValueMap<String,String> requestForm = new LinkedMultiValueMap<>();
			requestForm.add("grant_type","authorization_code");
			requestForm.add("code",authCode);
			requestForm.add("redirect_uri",redirectUri);
			requestForm.add("client_id",clientId);
			requestForm.add("client_secret",clientSecret);

			RequestEntity<LinkedMultiValueMap<String,String>> tokenRequest = RequestEntity.post(URI.create(authServletBase+"/token"))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
					.body(requestForm);

			logger.info("post for token");
			ResponseEntity<TokenResponse> res = restTemplate.exchange(tokenRequest,TokenResponse.class);
			logger.info("post for token done");
			if (!res.getStatusCode().is2xxSuccessful()) {
				return "AuthError";
			}
			tokenResponse = res.getBody();
			if (tokenResponse == null) {
				return "AuthError";
			}
			if (tokenResponse.error != null) {
				return "AuthError";
			}
		}
		{
			HashMap<String, String> variables = new HashMap<>();
			variables.put("access_token",tokenResponse.accessToken);
			variables.put("client_id",clientId);
			logger.info("get for user");
			ResponseEntity<UserResponse> res = restTemplate.getForEntity(authServletBase+"/verify?client_id={client_id}&access_token={access_token}", UserResponse.class, variables);
			logger.info("get for user done");
			if (!res.getStatusCode().is2xxSuccessful()) {
				return "AuthError";
			}
			UserResponse userResponse = res.getBody();
			if (userResponse == null) {
				return "AuthError";
			}
			Object user = userFactory.loadOrCreateUser(userResponse.id, userResponse.name);
			logger.info("Setting user to: {}",user);
			request.getSession().setAttribute("user",user);
		}
		logger.info("Redirecting to: {}",state);
		return "redirect:"+request.getContextPath()+state;
	}

	@GetMapping
	public String handleError(@RequestParam("error") String error, @RequestParam("state") String state){
		logger.info("handleError");
		return "AuthError";
	}


	public static class TokenResponse{
		@JsonProperty("access_token")
		public String accessToken;
		@JsonProperty("expires")
		public int expires;
		@JsonProperty
		public String error;

		public TokenResponse(){ }
	}

	public static class UserResponse {
		@JsonProperty("name")
		public String name;
		@JsonProperty("id")
		public long id;

		public UserResponse(){}
	}
}
