package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.*;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/debug")
public class DebugController {
    @Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	AuthorisationRepository authRepo;

	@RequestMapping("/apps")
	public Iterable<Application> apps()
	{
		return appRepo.findAll();
	}

	@RequestMapping("/users")
	public Iterable<User> users()
	{
		return userRepo.findAll();
	}

	@RequestMapping("/authorisations")
	public Iterable<Authorisation> authorisations()
	{
		return authRepo.findAll();
	}
}
