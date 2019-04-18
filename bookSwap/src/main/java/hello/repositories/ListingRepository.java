package hello.repositories;

import org.springframework.data.repository.CrudRepository;

import hello.models.Listing;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ListingRepository extends CrudRepository<Listing, Integer> {

}