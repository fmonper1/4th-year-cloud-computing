package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.Authorisation;
import ac.uk.shef.cc19grp10.auth.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerifyController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping
	public User verify(@FromAccessToken Authorisation auth)
	{
		return auth.getUser();
	}
}
