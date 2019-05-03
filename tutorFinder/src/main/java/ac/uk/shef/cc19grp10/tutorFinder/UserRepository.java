package ac.uk.shef.cc19grp10.tutorFinder;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByAuthId(long id);
}