package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.Account;
import ac.uk.shef.cc19grp10.payment.data.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pay")
public class PayController {

	@Autowired
	AccountRepository accountRepo;

//	@GetMapping("/{accountId}")
//	public @ResponseBody Object getPayAccount(
//			@PathVariable String accountId,
//			@RequestParam(value = "from", required = false) String senderAccountId,
//			@RequestParam(value = "amount", required = false) Integer amount,
//			@RequestParam(value = "callback", required = false) String callbackUri
//	) {
//
//
//
//		return new Object() {
//			public String account = accountId;
//			public String from = senderAccountId;
//			public Integer sending = amount;
//			public String callback = callbackUri;
//		};


//		return new ModelAndView("authorizePayment", "app", null);
//	}

//	@PostMapping("/{accountId}")
//	public @ResponseBody Object postPayAccount(
//			@PathVariable long accountId,
//			@RequestParam(value = "from") long senderAccountId,
//			@RequestParam(value = "amount") Integer amount,
//			@RequestParam(value = "callback", required = false) String callbackUri
//	) {
//		Account toAccount = accountRepo.findById(accountId).get();
//		Account fromAccount = accountRepo.findById(senderAccountId).get();
//
//		Account.transfer(fromAccount, toAccount, amount);
//
//		accountRepo.save(toAccount);
//		accountRepo.save(fromAccount);
//
//		return new Object() {
//			public long to = accountId;
//			public long from = senderAccountId;
//			public Integer sending = amount;
//			public String callback = callbackUri;
//		};
//	}

}
