package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import ac.uk.shef.cc19grp10.auth.security.SecureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
	UserRepository userRepo;

    @Autowired
	AuthorisationRepository authRepo;

	@Autowired
	ApplicationRepository appRepo;

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@RequestMapping("/users/{id}")
	public User users(@PathVariable long id, @RequestParam("auth_code") String authCodeEncoded, @RequestParam("client_id") String clientId)
	{
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()){
			return null;
		}
		Application app = appRepo.findByClientId(clientId);
		Authorisation auth = authRepo.findByUserAndApplication(user.get(),app);
		if(auth==null || !auth.checkAuthCodeEncoded(authCodeEncoded)){
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
		return user.get();
	}
}
