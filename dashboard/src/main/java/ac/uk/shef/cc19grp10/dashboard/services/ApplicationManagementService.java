package ac.uk.shef.cc19grp10.dashboard.services;

import ac.uk.shef.cc19grp10.dashboard.data.Application;
import ac.uk.shef.cc19grp10.dashboard.data.AuthApplication;
import ac.uk.shef.cc19grp10.dashboard.data.User;

/**
 * <Doc here>
 * <p>
 * Created on 18/04/2019.
 */
public interface ApplicationManagementService {
	Application createDbApplication(String applicationName, String dbPassword, User owner);

	AuthApplication getAuthApplication(User user) throws ApiError;

	AuthApplication createAuthApplication(String applicationName, String redirectUri, User user) throws ApiError;

	public class ApiError extends Exception{
	}
}
