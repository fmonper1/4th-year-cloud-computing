package ac.uk.shef.cc19grp10.payment.controllers;

import ac.uk.shef.cc19grp10.payment.data.*;
import ac.uk.shef.cc19grp10.payment.services.TransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for bill related user pages.
 */

@Controller
@RequestMapping("/bill")
public class BillController {

	private Logger logger = LoggerFactory.getLogger(BillController.class);

	private final BillRepository billRepo;
	private final AccountRepository accountRepo;

	private final TransactionManagement transactionManagement;

	public BillController(BillRepository billRepo, AccountRepository accountRepo, TransactionManagement transactionManagement) {
		this.billRepo = billRepo;
		this.accountRepo = accountRepo;
		this.transactionManagement = transactionManagement;
	}

	/**
	 * Presents the user with a page detailing their transaction and giving them the option to accept or decline.
	 * @param billId ID of the bill in question
	 * @param callback The URI in which to redirect to following a user accepting or declining the payment. The param
	 *                    'success' will be added.
	 * @return View either showing the accept/decline page or redirecting the user immediately if it is already paid.
	 */
	@GetMapping("/{billId}/pay")
	public ModelAndView getPayBill(
			@PathVariable long billId,
			@RequestParam(value = "callbackUri") String callback
	) throws URISyntaxException {
		Bill bill = billRepo.findById(billId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (bill.isPaid()) {
			return completePaymentAccepted(callback);
		}

		return new ModelAndView("bill/pay", "bill", bill);
	}

	/**
	 * Processes the response from the equivalent GET page's form. If payment is sucessful, it will redirect. Otherwise,
	 * it will show the user the error.
	 * @param billId ID of the bill in question
	 * @param callback The URI in which to redirect to following a user accepting or declining the payment. The param
	 *                    'success' will be added.
	 * @return View either redirecting the user or showing the accept/decline page with error.
	 */
	@PostMapping("/{billId}/pay")
	public ModelAndView postPayBill(
			@Valid BillForm billForm,
			@SessionAttribute User user,
			@PathVariable long billId,
			@RequestParam(value = "callbackUri") String callback
	) throws URISyntaxException {
		Map<String, Object> model = new HashMap<>();

		Bill bill = billRepo.findById(billId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (bill.isPaid()) {
			// Let's send them straight to their callback.
			return completePaymentAccepted(callback);
		}

		model.put("bill", bill);

		if (billForm.getAccept() != null) {
			boolean paymentAccepted = billForm.getAccept().equals("yes");
			boolean paymentDeclined = billForm.getAccept().equals("no");

			if (paymentAccepted) {
				Account fromAccount = accountRepo.findAccountByOwner(user)
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

				try {
					transactionManagement.createTransactionFromBill(bill, fromAccount);
				} catch (TransactionManagement.InsufficientFundsError e) {
					// Show the user the error back on the bill page.
					model.put("error", "Your balance is insufficient");
					return new ModelAndView("bill/pay", model);
				}

				return completePaymentAccepted(callback);
			} else if (paymentDeclined) {
				return completePaymentDeclined(callback);
			}

		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Redirects the user with failure
	 * @param redirect The URI in which to redirect the user to.
	 * @return Redirection view.
	 * @throws URISyntaxException Thrown with an invalid redirect String.
	 */
	private ModelAndView completePaymentDeclined(String redirect) throws URISyntaxException {
		String params = "success=0";
		return completeAuthorisation(redirect, params);
	}


	/**
	 * Redirects the user with success
	 * @param redirect The URI in which to redirect the user to.
	 * @return Redirection view.
	 * @throws URISyntaxException Thrown with an invalid redirect String.
	 */
	private ModelAndView completePaymentAccepted(String redirect) throws URISyntaxException {
		String params = "success=1";
		return completeAuthorisation(redirect, params);
	}

	/**
	 * Redirects the user
	 * @param redirect The URI in which to redirect the user to.
	 * @return Redirection view.
	 * @throws URISyntaxException Thrown with an invalid redirect String.
	 */
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

	/**
	 * POJO for Spring to insert form data into.
	 */
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
