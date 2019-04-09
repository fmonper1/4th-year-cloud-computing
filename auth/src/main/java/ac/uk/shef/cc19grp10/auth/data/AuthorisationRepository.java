package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorisationRepository extends CrudRepository<Authorisation, Long> {
	Authorisation findByUserAndApplication(User user, Application application);
	Optional<Authorisation> findByAuthCode(byte[] authCode);
}
