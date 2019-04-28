package ac.uk.shef.cc19grp10.payment;

import ac.uk.shef.cc19grp10.payment.data.*;
import ac.uk.shef.cc19grp10.payment.services.TransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bill")
public class BillController {

	private Logger logger = LoggerFactory.getLogger(BillController.class);

	@Autowired
	BillRepository billRepo;
	@Autowired
	AccountRepository accountRepo;

	@Autowired
	TransactionManagement transactionManagement;

	@PostMapping
	public @ResponseBody Object createBill(
			@RequestParam(value = "toAccount") long accountId,
			@RequestParam(value = "amount") int amount
	) {
		Account toAccount = accountRepo.findById(accountId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		return billRepo.save(new Bill(toAccount, amount));
	}

	@GetMapping("/{billId}")
	public ModelAndView getPayBill(
			@PathVariable long billId,
			@RequestParam(value = "callbackUri") String callback,
			@RequestParam(value = "state", required = false) String state,
			@SessionAttribute("user") User user
	) throws URISyntaxException {
		Bill bill = billRepo.findById(billId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (bill.isPaid()) {
			return completePaymentSuccess(callback, state);
		}

		return new ModelAndView("payBill", "bill", bill);
	}

	@PostMapping("/{billId}")
	public ModelAndView postPayBill(
			@Valid BillForm billForm,
			@SessionAttribute("user") User user,
			@PathVariable long billId,
			@RequestParam(value = "callbackUri") String callback,
			@RequestParam(value = "state", required = false) String state
	) throws URISyntaxException {
		Map<String, Object> model = new HashMap<>();

		Bill bill = billRepo.findById(billId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (bill.isPaid()) {
			return completePaymentSuccess(callback, state);
		}

		model.put("bill", bill);

		if (billForm.getAccept() != null && billForm.getAccept().equals("yes")) {
			Account fromAccount = user.getAccount();

			try {
				transactionManagement.createTransactionFromBill(bill, fromAccount);
			} catch (TransactionManagement.InsufficientFundsError e) {
				model.put("error", "Your balance is insufficient");
				return new ModelAndView("payBill", model);
			}

			return completePaymentSuccess(callback, state);
		} else if (billForm.getAccept() != null && billForm.getAccept().equals("no")) {
			return completePaymentFailed(callback, state);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	private ModelAndView completePaymentFailed(String redirect, String state) throws URISyntaxException {
		String authParams = "success=0";
		if (state != null) {
			authParams += "&state=" + state;
		}
		return completeAuthorisation(redirect, authParams);
	}

	private ModelAndView completePaymentSuccess(String redirect, String state) throws URISyntaxException {
		String authParams = "success=1";
		if (state != null) {
			authParams += "&state=" + state;
		}
		return completeAuthorisation(redirect, authParams);
	}

	private ModelAndView completeAuthorisation(String redirect, String authParams) throws URISyntaxException {
		URI redirectUri = new URI(redirect);
		if (redirectUri.getQuery() == null || redirectUri.getQuery().isEmpty()) {
			redirect += "?";
		} else {
			redirect += "&";
		}
		redirect += authParams;
		return new ModelAndView("redirect:" + redirect);
	}

	public class BillForm {
		private String accept;

		public String getAccept() {
			return accept;
		}

		public void setAccept(String accept) {
			this.accept = accept;
		}
	}
}
