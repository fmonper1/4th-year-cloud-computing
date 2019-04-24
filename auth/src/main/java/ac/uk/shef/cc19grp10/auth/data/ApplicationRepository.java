package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Application findByClientId(String clientId);
}
