package ac.uk.shef.cc19grp10.payment.controllers;

import ac.uk.shef.cc19grp10.payment.data.Account;
import ac.uk.shef.cc19grp10.payment.data.AccountRepository;
import ac.uk.shef.cc19grp10.payment.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * Rest API Controller for Account entity
 */

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountAPI {

	@Autowired
	AccountRepository accountRepo;

	private Logger logger = LoggerFactory.getLogger(AccountAPI.class);

	@GetMapping
	public Account getAccountForCurrentUser(@SessionAttribute User user)
	{
		return accountRepo.findAccountByOwner(user)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

}
