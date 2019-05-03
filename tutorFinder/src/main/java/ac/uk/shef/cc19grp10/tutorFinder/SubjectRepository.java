
package ac.uk.shef.cc19grp10.tutorFinder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ac.uk.shef.cc19grp10.tutorFinder.Subject;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    List<Subject> findByName(String name);

}