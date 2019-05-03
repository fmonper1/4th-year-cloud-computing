package ac.uk.shef.cc19grp10.payment.controllers;

import ac.uk.shef.cc19grp10.payment.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Controller for account related user pages.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);

	private final AccountRepository accountRepo;

	public AccountController(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	@GetMapping
	public ModelAndView getAccountOfCurrentUser(
			@SessionAttribute User user
	) throws AccountTransactionWrapper.AccountTransactionMismatchError {
		Map<String, Object> model = new HashMap<>(); // Allows you to inject multiple variables into view

		Account account = accountRepo.findAccountByOwner(user)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		model.put("account", account);

		// Generate transactions with decorated methods for current account
		Set<AccountTransactionWrapper> decoratedTransactions = new HashSet<>();
		for (Transaction transaction : account.getTransactions()) {
			decoratedTransactions.add(new AccountTransactionWrapper(account, transaction));
		}

		model.put("transactions", decoratedTransactions);

		return new ModelAndView("account/show", model);
	}
}
