package ac.uk.shef.cc19grp10.auth;

import ac.uk.shef.cc19grp10.auth.data.Application;
import ac.uk.shef.cc19grp10.auth.data.ApplicationRepository;
import ac.uk.shef.cc19grp10.auth.data.User;
import ac.uk.shef.cc19grp10.auth.data.UserRepository;
import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DebugController {

    private final Logger logger = LoggerFactory.getLogger(DebugController.class);

	@Autowired
    HashingStrategy hashingStrategy;

    @Autowired
	UserRepository userRepo;

	@Autowired
	ApplicationRepository appRepo;

	@RequestMapping("/apps/{id}")
	public Optional<Application> apps(@PathVariable long id)
	{
		return appRepo.findById(id);
	}

	@RequestMapping("/users/{id}")
	public Optional<User> users(@PathVariable long id)
	{
		return userRepo.findById(id);
	}
}
