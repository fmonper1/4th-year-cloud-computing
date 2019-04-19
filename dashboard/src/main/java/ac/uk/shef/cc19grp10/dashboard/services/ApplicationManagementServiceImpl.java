package ac.uk.shef.cc19grp10.dashboard.services;

import ac.uk.shef.cc19grp10.dashboard.data.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <Doc here>
 * <p>
 * Created on 18/04/2019.
 */
@Service
public class ApplicationManagementServiceImpl implements ApplicationManagementService {

	@Autowired
	private ApplicationRepository appRepo;

	@Autowired
	private DbManagementRepository dbManagement;

	@Autowired
	private TransactionTemplate transactionTemplate;

	private RestTemplate restTemplate;

	public ApplicationManagementServiceImpl(){
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
	}

	@Value("${auth.auth_servlet_url}")
	private String authServletUrl;
	private Logger logger = LoggerFactory.getLogger(ApplicationManagementServiceImpl.class);

	@Override
	public Application createDbApplication(String applicationName, String dbPassword, User owner){
		String clientId = nameToClientId(applicationName);
		String dbUsername = clientId;
		String applicationUrl = clientId;
		String schemaName = nameToSchemaName(applicationName);

		//TODO: scan for image path
		String imagePath = null;

		return transactionTemplate.execute(status ->{
			Application app = appRepo.save(new Application(applicationName, applicationUrl, imagePath, "TODO: descriptions", dbUsername, schemaName, owner));
			//using clientId as db username and schema name also
			dbManagement.createDbUser(dbUsername,dbPassword);
			dbManagement.createDbSchema(schemaName);
			dbManagement.grantPrivileges(schemaName,clientId);
			return app;
		});
	}

	@Override
	public AuthApplication getAuthApplication(User user) throws ApiError {
		//TODO: how to re-aquire access token if it's expired. (alternatively don't expire access tokens)
		HashMap<String,String> accessToken = new HashMap<>();
		logger.info("access_token: {}", user.getAccessToken());
		accessToken.put("accessToken",user.getAccessToken());

		ResponseEntity<AuthApplication> res = restTemplate.getForEntity(authServletUrl+"/developer?access_token={accessToken}",AuthApplication.class,accessToken);
		if (!res.getStatusCode().is2xxSuccessful()){
			throw new ApiError();
		}

		AuthApplication authApp = res.getBody();
		if (authApp == null){
			throw new ApiError();
		}
		return authApp;
	}

	@Override
	public AuthApplication createAuthApplication(String applicationName, String redirectUri, User owner) throws ApiError{
		String clientId = nameToClientId(applicationName);
		String dbUsername = clientId;
		String applicationUrl = clientId;

		//TODO: how to re-aquire access token if it's expired. (alternatively don't expire access tokens)
		HashMap<String,String> accessToken = new HashMap<>();
		logger.info("access_token: {}", owner.getAccessToken());
		accessToken.put("accessToken",owner.getAccessToken());

		//try create the auth part of the app
		ResponseEntity<AuthApplication> res;
		try {
			res = restTemplate.postForEntity(authServletUrl + "developer/create?access_token={accessToken}", new CreateAuthApplicationRequest(applicationName,redirectUri), AuthApplication.class, accessToken);
		}catch(HttpClientErrorException.BadRequest badReq){
			logger.info("Bad request response body: {}", badReq.getResponseBodyAsString());
			throw new ApiError();
		}

		if (!res.getStatusCode().is2xxSuccessful()){
			throw new ApiError();
		}

		AuthApplication authApp = res.getBody();
		if (authApp == null){
			throw new ApiError();
		}

		return authApp;
	}

	/**
	 * Create a normalised, schema safe
	 *
	 * Replaces non-ascii characters with a close equivalent,
	 * and removes non-alphanumeric characters
	 */
	private String nameToSchemaName(String applicationName) {
		//unicode normalise
		return Normalizer.normalize(applicationName, Normalizer.Form.NFD)
				//replace space with dash
				.replaceAll("\\p{Space}","")
				//remove non alphanumeric/dash characters
				.replaceAll("[^\\p{Alnum}]", "")
				//and lowercase it
				.toLowerCase();
	}

	/**
	 * Create a normalised, url-safe client id slug.
	 *
	 * Replaces non-ascii characters with a close equivalent,
	 * and removes non-alphanumeric characters
	 */
	private String nameToClientId(String applicationName) {
		//unicode normalise
		return "app_"+Normalizer.normalize(applicationName, Normalizer.Form.NFD)
				//replace space with dash
				.replaceAll("\\p{Space}","-")
				//remove non alphanumeric/dash characters
				.replaceAll("[^\\p{Alnum}-]", "")
				//and lowercase it
				.toLowerCase();
	}

	private static class CreateAuthApplicationRequest {
		@JsonProperty
		String applicationName;
		@JsonProperty
		String redirectUri;

		public CreateAuthApplicationRequest(String applicationName, String redirectUri){
			this.applicationName = applicationName;
			this.redirectUri = redirectUri;
		}
	}
}
