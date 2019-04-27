package ac.uk.shef.cc19grp10.payment.data;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {}
