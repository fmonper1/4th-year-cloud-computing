package ac.uk.shef.cc19grp10.payment.data;

import ac.uk.shef.cc19grp10.utils.login.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserFactoryImpl implements UserFactory {

    private Logger logger = LoggerFactory.getLogger(UserFactoryImpl.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    AccountRepository accountRepo;

    @Override
    public User loadOrCreateUser(long idFromAuthService, String name, String accessToken) {
        logger.debug("loadOrCreateUser called");
        Optional maybeUser = userRepo.findById(idFromAuthService);

        User user;

        if (maybeUser.isPresent()) {
            logger.info("User existed already, updating...");
            user = (User) maybeUser.get();
            updateUserWithNewDetails(user, name, accessToken);
            logger.info("Updated.");
        } else {
            logger.info("User didn't already exist, creating user and account...");
            user = createNewUserAndAccount(idFromAuthService, name, accessToken);
            logger.info("Created.");
        }

        return user;
    }

    private void updateUserWithNewDetails(User user, String name, String accessToken) {
        boolean accessTokenChanged = !user.getAccessToken().equals(accessToken);
        boolean nameChanged = !user.getName().equals(name);

        if (accessTokenChanged || nameChanged) {
            user.setAccessToken(accessToken);
            user.setName(name);
            userRepo.save(user);
        }
    }

    private User createNewUserAndAccount(long authUserId, String name, String accessToken) {
        User newUser = new User(authUserId, name, accessToken);
        userRepo.save(newUser);

        Account newAccount = Account.newUserAccount();
        newAccount.setOwner(newUser);
        accountRepo.save(newAccount);

        return newUser;
    }
}
