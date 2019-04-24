package ac.uk.shef.cc19grp10.dashboard.services;

import ac.uk.shef.cc19grp10.dashboard.data.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <Doc here>
 * <p>
 * Created on 18/04/2019.
 */
public interface ApplicationManagementService {
	DbApplication createDbApplication(String dbPassword, Application application);

	AuthApplication getAuthApplication(User user) throws ApiError;

	AuthApplication createAuthApplication(String redirectUri, User owner, Application application) throws ApiError;

	Application createApplication(String applicationName, String description, User owner);

	Deployment createDeployment(MultipartFile warFile, Application application) throws ApiError;

	public class ApiError extends Exception{
	}
}
