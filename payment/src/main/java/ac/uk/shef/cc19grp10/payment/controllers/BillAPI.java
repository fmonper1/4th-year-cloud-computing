package ac.uk.shef.cc19grp10.payment.controllers;

import ac.uk.shef.cc19grp10.payment.data.*;
import ac.uk.shef.cc19grp10.payment.services.TransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/bills")
public class BillAPI {

	private Logger logger = LoggerFactory.getLogger(BillAPI.class);

	@Autowired
	BillRepository billRepo;
	@Autowired
	AccountRepository accountRepo;

	@Autowired
	TransactionManagement transactionManagement;

	@PostMapping
	public Bill create(
			@RequestParam(value = "toAccount") long toAccountId,
			@RequestParam(value = "amount") int amount
	) {
		Account toAccount = accountRepo.findById(toAccountId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		Bill newBill = new Bill(toAccount, amount);

		return billRepo.save(newBill);
	}

	@GetMapping("/{billId}")
	public Bill findById(@PathVariable long billId) {
		return billRepo.findById(billId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
