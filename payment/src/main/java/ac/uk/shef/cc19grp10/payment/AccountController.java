package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.Account;
import ac.uk.shef.cc19grp10.payment.data.AccountRepository;
import ac.uk.shef.cc19grp10.payment.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountRepository accountRepo;

	private Logger logger = LoggerFactory.getLogger(AccountController.class);

	@GetMapping("/balance")
	public @ResponseBody Object getBalance(
			@SessionAttribute("user") User user,
			@RequestParam(value = "accountId", required = false) Long id
	)
	{
		Account account;

		if (id == null) {
			logger.info("No account ID provided, getting authenticated user's account");
			account = accountRepo.findAccountByOwner(user)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
		} else {
			logger.info("Account ID provided, getting accoun by ID");
			account = accountRepo.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		}

		return account;
	}

}
