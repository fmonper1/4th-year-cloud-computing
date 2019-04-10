package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    @Autowired
	UserRepository userRepo;

    @Autowired
	AuthorisationRepository authRepo;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	AccessTokenRepository accessTokenRepo;

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping
	public User verify(@RequestParam("access_token") String accessTokenEncoded, @RequestParam("client_id") String clientId)
	{
		Application app = appRepo.findByClientId(clientId);
		AccessToken accessToken = accessTokenRepo.findByAccessToken(Base64.getUrlDecoder().decode(accessTokenEncoded));
		if(accessToken==null){
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		Authorisation auth = accessToken.getAuthorisation();
		if(auth==null){
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		if(!auth.getApplication().equals(app)){
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		return auth.getUser();
	}
}
