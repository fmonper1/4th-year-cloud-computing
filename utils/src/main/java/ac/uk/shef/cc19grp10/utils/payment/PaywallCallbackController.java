package ac.uk.shef.cc19grp10.utils.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@Controller
@RequestMapping("/payment/callback")
@ConditionalOnProperty(prefix= "payment.paywall.", name="access_charge")
public class PaywallCallbackController {

	@Autowired
	private BillUtility billUtility;

	@Value("${payment.callback_uri}")
	private String callbackUri;

	private Logger logger = LoggerFactory.getLogger(PaywallCallbackController.class);

	@GetMapping
	public ModelAndView callback(
			@SessionAttribute long billId,
			@RequestParam boolean success,
			@RequestParam("state") String encodedState
	) throws CannotVerifyBillException, UnsupportedEncodingException {
		String state = new String(Base64.getUrlDecoder().decode(encodedState));
		if (success) {
			return handleAccept(billId, state);
		} else {
			return handleReject(billId, state);
		}
	}

	private ModelAndView handleAccept(long billId, String state) throws CannotVerifyBillException, UnsupportedEncodingException {
		if (billUtility.verifyBillPaid(billId)) {
			logger.info("Redirecting to: {}", state);
			return new ModelAndView(new RedirectView(state,true));
		} else {
			return handleReject(billId, state);
		}
	}

	private ModelAndView handleReject(Long billId, String state) throws UnsupportedEncodingException {
		Map<String, Object> model = new HashMap<>();

		String redirectUri = callbackUri + "?state=" + Base64.getUrlEncoder().encodeToString(state.getBytes());

		model.put("billId", billId);
		model.put("payUri", billUtility.getBillPaymentUri(billId, redirectUri));

		return new ModelAndView("paywall", model);
	}
}
