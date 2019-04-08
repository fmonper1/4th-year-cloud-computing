package ac.uk.shef.cc19grp10.auth.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<User, Long> {

    List<User> findBySomeField(String someField);
}
