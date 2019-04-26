package ac.uk.shef.cc19grp10.payment.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findAccountByOwner(User owner);
}
