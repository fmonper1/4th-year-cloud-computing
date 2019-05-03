package ac.uk.shef.cc19grp10.bookswap.repositories;

import org.springframework.data.repository.CrudRepository;

import ac.uk.shef.cc19grp10.bookswap.models.User;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByAuthId(long id);

}