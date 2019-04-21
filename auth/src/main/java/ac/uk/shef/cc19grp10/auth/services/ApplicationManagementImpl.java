package ac.uk.shef.cc19grp10.auth.services;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

/**
 * <Doc here>
 * <p>
 * Created on 13/04/2019.
 */
@Service
public class ApplicationManagementImpl implements ApplicationManagement {

	@Autowired
	private ApplicationRepository appRepo;

	@Override
	public Application createApplication(String redirectUri, String applicationName, User owner) throws ApplicationExistsError {
		String clientId = nameToClientId(applicationName);
		Application existingApplication = appRepo.findByClientId(clientId);
		if(existingApplication != null){
			throw new ApplicationExistsError();
		}
		return appRepo.save(new Application(applicationName, clientId, owner, redirectUri));
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

}
