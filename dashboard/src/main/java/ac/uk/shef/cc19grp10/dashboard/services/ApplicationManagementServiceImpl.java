package ac.uk.shef.cc19grp10.dashboard.services;

import ac.uk.shef.cc19grp10.dashboard.data.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

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
	private DeploymentRepository deploymentRepo;

	@Autowired
	private DbApplicationRepository dbAppRepo;

	@Autowired
	private DbManagementRepository dbManagement;

	@Autowired
	private TransactionTemplate transactionTemplate;

	private static final String MANAGER_APP_BASE_URL = "http://143.167.9.214:8080/manager";

	private RestTemplate restTemplate;

	public ApplicationManagementServiceImpl(){
		restTemplate = new RestTemplate();
		restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
	}

	@Value("${auth.auth_servlet_url}")
	private String authServletUrl;
	private Logger logger = LoggerFactory.getLogger(ApplicationManagementServiceImpl.class);

	@Override
	public DbApplication createDbApplication(String dbPassword, Application application){
		String clientId = nameToClientId(application.getName());
		String dbUsername = clientId;
		String applicationUrl = clientId;
		String schemaName = nameToSchemaName(application.getName());

		//TODO: scan for image path
		String imagePath = null;

		return transactionTemplate.execute(status ->{
			DbApplication app = dbAppRepo.save(new DbApplication(dbUsername,schemaName, application));
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
	public AuthApplication createAuthApplication(String redirectUri, User owner, Application application) throws ApiError{
		String clientId = nameToClientId(application.getName());
		String dbUsername = clientId;
		String applicationUrl = clientId;

		//TODO: how to re-aquire access token if it's expired. (alternatively don't expire access tokens)
		HashMap<String,String> accessToken = new HashMap<>();
		logger.info("access_token: {}", owner.getAccessToken());
		accessToken.put("accessToken",owner.getAccessToken());

		//try create the auth part of the app
		ResponseEntity<AuthApplication> res;
		try {
			res = restTemplate.postForEntity(authServletUrl + "developer/create?access_token={accessToken}", new CreateAuthApplicationRequest(application.getName(),redirectUri), AuthApplication.class, accessToken);
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

	public Application createApplication(String applicationName, String description, User owner){
		return appRepo.save(new Application(applicationName, description, owner));
	}

	@Override
	public Deployment createDeployment(MultipartFile warFile, Application application) throws ApiError {
		String clientId = nameToClientId(application.getName());
		String url = "/"+clientId;
		MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();
		body.add("file",warFile);
		RequestEntity request = RequestEntity.put(URI.create(MANAGER_APP_BASE_URL +"/text/deploy?update=true&path="+url))
				.body(body);
		ResponseEntity<String> res = restTemplate.exchange(request,String.class);
		if(!res.getStatusCode().is2xxSuccessful()){
			logger.warn("Manager deploy responded with status code: {}",res.getStatusCode().value());
			throw new ApiError();
		}
		String resBody = res.getBody();
		if(resBody == null){
			logger.warn("Manager deploy responded with no body");
			throw new ApiError();
		}

		if(!resBody.startsWith("OK - ")){
			logger.warn("Manager deploy responded with: {}",resBody);
			throw new ApiError();
		}

		//determine image path
		String imagePath = null;

		File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
		File webinfDirectory = new File( catalinaBase, "webapps/"+clientId+"/WEB-INF/" );

		//look for image.png image.svg, or image.jpg
		PathMatcher imageMatcher =
				FileSystems.getDefault().getPathMatcher("glob:image.{png,jpg,svg}");
		//look for image.png image.svg, or image.jpg in the web-inf directory of an application
		File[] images = webinfDirectory.listFiles(pathname -> imageMatcher.matches(pathname.toPath()));

		String bestImagePath = null;

		if(images != null && images.length > 0){
			//TODO: better metric for best image. probably something like largest and closest to optimal aspect ratio would be best
			Optional<File> svg = Arrays.stream(images).filter(image -> image.toPath().endsWith(".svg")).findAny();
			Optional<File> png = Arrays.stream(images).filter(image -> image.toPath().endsWith(".png")).findAny();
			File bestImage = svg.orElse(png.orElse(images[0]));
			bestImagePath = bestImage.getAbsolutePath();
		}

		return deploymentRepo.save(new Deployment(url,bestImagePath,application));
	}

	/**
	 * Create a normalised, schema safe
	 *
	 * Replaces non-ascii characters with a close equivalent,
	 * and removes non-alphanumeric characters
	 */
	private String nameToSchemaName(String applicationName) {
		//unicode normalise
		return "app_"+Normalizer.normalize(applicationName, Normalizer.Form.NFD)
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
				.replaceAll("\\p{Space}","_")
				//remove non alphanumeric/dash characters
				.replaceAll("[^\\p{Alnum}_]", "")
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
