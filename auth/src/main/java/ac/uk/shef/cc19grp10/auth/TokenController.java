package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final Logger logger = LoggerFactory.getLogger(TokenController.class);

	@Autowired
    HashingStrategy hashingStrategy;

    @Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	AuthorisationRepository authRepo;

	@Autowired
	AccessTokenRepository accessTokenRepo;

	//POST https://api.authorization-server.com/token
	//  grant_type=authorization_code&
	//  code=AUTH_CODE_HERE&
	//  redirect_uri=REDIRECT_URI&
	//  client_id=CLIENT_ID&
	//  client_secret=CLIENT_SECRET
	@PostMapping(produces = "application/json")
	public AccessToken auth(
			@RequestParam("code") String encodedAuthCode,
			@RequestParam("redirect_uri") String redirect,
			@RequestParam("client_id") String clientId,
			@RequestParam("client_secret") String clientSecret) throws URISyntaxException {
		Optional<Authorisation> optAuthorisation = authRepo.findByAuthCode(Base64.getUrlDecoder().decode(encodedAuthCode));
		if (!optAuthorisation.isPresent()){
			logger.info("Authorisation not created");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid_request");
		}
		Authorisation authorisation = optAuthorisation.get();

		if (!authorisation.getApplication().getClientId().equals(clientId)){
			logger.info("Authorisation client id's don't match");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid_request");
		}

		if (!authorisation.getApplication().checkEncodedClientSecret(clientSecret)){
			logger.info("Authorisation client secrets don't match, expected: {}",authorisation.getApplication().getEncodedClientSecret());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"invalid_request");
		}

		Date expiry = Date.from(Instant.now().plus(Duration.ofDays(7)));
		authorisation.setAccessToken(new AccessToken(expiry));
		authRepo.save(authorisation);
		return authorisation.getAccessToken();
	}
}
