package ac.uk.shef.cc19grp10.utils.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;

@Component
public class PaywallInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(PaywallInterceptor.class);

	private static final String BILL_EXPIRY_DATE_ATTR = "bill_expiry";
	private static final String BILL_ID_ATTR = "billId";

	@Value("${payment.paywall.access_charge}")
	private Integer paywallAccessCharge;

	@Value("${payment.paywall.access_duration}")
	private Long paywallAccessDurationInHours;

	@Value("${payment.callback_uri}")
	private String callbackUri;

	@Autowired
	BillUtility billUtility;

	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler
	) throws CannotCreateBillException, CannotVerifyBillException, IOException {
		HttpSession session = request.getSession();
		Long billId = getBillIdFromSession(session);

		logger.info("Checking if the bill is paid... {}", request.getRequestURI());
		String state = request.getRequestURI().substring(request.getContextPath().length());

		if (billId == null) {
			logger.info("No valid bill found, creating new bill");
			billId = billUtility.createBill(paywallAccessCharge);
			putBillIdInSession(session, billId);
			billUtility.redirectUserToBillPayment(response, billId, callbackUri + "?state=" + Base64.getUrlEncoder().encodeToString(state.getBytes()));
			return false;
		} else {
			logger.info("Valid bill found, verifying...");
			if (billUtility.verifyBillPaid(billId)) {
				logger.info("Bill paid, letting user through paywall");
				return true;
			} else {
				logger.info("Bill unpaid, sending user to empty their pockets");
				billUtility.redirectUserToBillPayment(response, billId, callbackUri + "?state=" + Base64.getUrlEncoder().encodeToString(state.getBytes()));
				return false;
			}
		}
	}

	private void putBillIdInSession(HttpSession session, long billId) {
		LocalDateTime expiryDate = LocalDateTime.now().plusHours(paywallAccessDurationInHours);

		session.setAttribute(BILL_ID_ATTR, billId);
		session.setAttribute(BILL_EXPIRY_DATE_ATTR, expiryDate);
	}

	private Long getBillIdFromSession(HttpSession session) {
		LocalDateTime billExpiry = (LocalDateTime) session.getAttribute(BILL_EXPIRY_DATE_ATTR);

		if (billExpiry == null || billExpiry.isBefore(LocalDateTime.now())) {
			return null;
		}

		return (Long) session.getAttribute(BILL_ID_ATTR);
	}
}
