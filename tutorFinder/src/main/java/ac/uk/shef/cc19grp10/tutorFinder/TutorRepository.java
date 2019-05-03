
package ac.uk.shef.cc19grp10.tutorFinder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ac.uk.shef.cc19grp10.tutorFinder.Tutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TutorRepository extends CrudRepository<Tutor, Long> {

    //List<Tutor> findByLastName(String lastName);

    //List<Tutor> findAllByFirstNameContainingOrLastNameContaining(String name);

    @Query("SELECT u FROM Tutor u WHERE u.firstName = :name OR u.lastName = :name")
    List<Tutor>findByName(@Param("name") String name);

}