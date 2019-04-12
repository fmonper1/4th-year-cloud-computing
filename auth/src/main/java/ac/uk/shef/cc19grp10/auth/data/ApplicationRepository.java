package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Application findByClientId(String clientId);
    Application findByOwner(User owner);
	boolean existsByClientId(String clientId);
}
