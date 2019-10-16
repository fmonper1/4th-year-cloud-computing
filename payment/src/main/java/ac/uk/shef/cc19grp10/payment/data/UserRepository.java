package ac.uk.shef.cc19grp10.payment.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getUserByAuthId(Long authId);
}
