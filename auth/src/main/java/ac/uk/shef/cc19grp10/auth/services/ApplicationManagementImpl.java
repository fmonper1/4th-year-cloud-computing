package ac.uk.shef.cc19grp10.auth.services;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.DbManagementRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
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

	@Autowired
	private DbManagementRepository dbManagement;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@Override
	public Application createApplication(String redirectUri, String applicationName, User owner, String dbPassword) throws ApplicationExistsError {
		String clientId = nameToClientId(applicationName);
		String schemaName = nameToSchemaName(applicationName);
		Application existingApplication = appRepo.findByClientId(clientId);
		if(existingApplication != null){
			throw new ApplicationExistsError();
		}
		existingApplication = appRepo.findBySchemaName(schemaName);
		if(existingApplication != null){
			throw new ApplicationExistsError();
		}
		return transactionTemplate.execute(status ->{
			//using clientId as db username and schema name also
			Application app = appRepo.save(new Application(applicationName, clientId, clientId, schemaName, owner, redirectUri));
			dbManagement.createDbUser(clientId,dbPassword);
			dbManagement.createDbSchema(schemaName);
			dbManagement.grantPrivileges(schemaName,clientId);
			return app;
		});
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

}
