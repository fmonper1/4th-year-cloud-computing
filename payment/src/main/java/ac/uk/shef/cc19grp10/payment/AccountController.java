package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.Account;
import ac.uk.shef.cc19grp10.payment.data.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static final int DEFAULT_INITIAL_BALANCE = 500;

	@Autowired
	AccountRepository accountRepo;

	private Logger logger = LoggerFactory.getLogger(AccountController.class);

	@GetMapping("/balance")
	public @ResponseBody Object getBalance(
			@RequestParam(value = "accountId", required = true) Long id
	)
	{
		Account account = accountRepo.findById(id).get();
		return new BalanceResult(account.getId(), 500);
	}

	@PostMapping("/new-random-account")
	public @ResponseBody Account newRandAccount() {
		Account newAccount = new Account(DEFAULT_INITIAL_BALANCE);
		return accountRepo.save(newAccount);
	}

	class BalanceResult {
		public long userId;
		public int value;

		BalanceResult(long userId, int value) {
			this.userId = userId;
			this.value = value;
		}
	}

}
