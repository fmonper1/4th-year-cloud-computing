package ac.uk.shef.cc19grp10.bookswap.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ac.uk.shef.cc19grp10.bookswap.models.Listing;
import org.springframework.data.repository.query.Param;

import java.util.Set;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ListingRepository extends CrudRepository<Listing, Integer> {

    /**
     * Finds a person by using the last name as a search criteria.
     * @param userId the user id
     * @return  A set of the Listings created by a user
     */
    @Query("FROM Listing l WHERE l.user.id = ?1")
    public Iterable<Listing> findByUserId(Integer userId);

    @Query("FROM Listing l WHERE l.title LIKE %?1% OR l.moduleCode LIKE %?1%")
    public Iterable<Listing> findByTitleOrModule(String searchParam);

}