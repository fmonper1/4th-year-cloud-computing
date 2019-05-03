package ac.uk.shef.cc19grp10.dashboard.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
	Application findByOwner(User user);
	@Query("SELECT dep.application FROM Deployment dep")
	List<Application> findAllDeployed();
}
