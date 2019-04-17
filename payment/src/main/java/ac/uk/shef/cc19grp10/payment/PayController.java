package ac.uk.shef.cc19grp10.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/pay")
public class PayController {

    private final Logger logger = LoggerFactory.getLogger(PayController.class);

	@Autowired
	public RequestMappingHandlerMapping requestMappingHandlerMapping;

	@GetMapping("/{accountId}")
	public @ResponseBody Object getPayAccount(
			@PathVariable String accountId,
			@RequestParam(value = "from", required = false) String senderAccountId,
			@RequestParam(value = "amount", required = false) Integer amount,
			@RequestParam(value = "callback", required = false) String callbackUri
	) {
		return new Object() {
			public String account = accountId;
			public String from = senderAccountId;
			public Integer sending = amount;
			public String callback = callbackUri;
		};


//		return new ModelAndView("authorizePayment", "app", null);
	}

	@GetMapping("/endpoints")
	public @ResponseBody
	Object showEndpointsAction()
	{
		return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(t ->
				(t.getMethodsCondition().getMethods().size() == 0 ? "GET" : t.getMethodsCondition().getMethods().toArray()[0]) + " " +
						t.getPatternsCondition().getPatterns().toArray()[0]
		).toArray();
	}

}
