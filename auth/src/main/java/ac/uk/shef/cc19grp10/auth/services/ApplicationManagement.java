package ac.uk.shef.cc19grp10.auth.services;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.User;

/**
 * <Doc here>
 * <p>
 * Created on 13/04/2019.
 */
public interface ApplicationManagement {
	Application createApplication(String redirectUri, String applicationName, User owner, String dbPassword) throws ApplicationExistsError;

	class ApplicationExistsError extends Throwable {
	}
}
