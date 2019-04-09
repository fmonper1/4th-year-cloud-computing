package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.data.repository.CrudRepository;

public interface AuthorisationRepository extends CrudRepository<Authorisation, Long> {
	Authorisation findByUserAndApplication(User user, Application application);
}
