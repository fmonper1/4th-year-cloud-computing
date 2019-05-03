package ac.uk.shef.cc19grp10.payment.data;

import ac.uk.shef.cc19grp10.utils.login.UserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Provides a user factory implementation which is used in the AuthCallbackController. When auth calls back, it calls
 * the public method described in the UserFactory interface.
 */

public class UserFactoryImpl implements UserFactory {

    private Logger logger = LoggerFactory.getLogger(UserFactoryImpl.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    AccountRepository accountRepo;

    /**
     * Implementation of the primary method of the UserFactory interface.
     * @param idFromAuthService Identifier provided by the auth microservice.
     * @param name A name describing the user in the auth microservice.
     * @param accessToken Access token provided by the auth microservice. Always updated to ensure authorization
     *                    information is up to date.
     * @return The produced User entity either recently created or loaded.
     */
    @Override
    public User loadOrCreateUser(long idFromAuthService, String name, String accessToken) {
        logger.debug("loadOrCreateUser called");
        Optional<User> maybeUser = userRepo.getUserByAuthId(idFromAuthService);

        User user;

        if (maybeUser.isPresent()) {
            logger.info("User existed already, updating...");
            user = maybeUser.get();
            updateUserWithNewDetails(user, name, accessToken);
            logger.info("Updated.");
        } else {
            logger.info("User didn't already exist, creating user and account...");
            user = createNewUserAndAccount(idFromAuthService, name, accessToken);
            logger.info("Created.");
        }

        return user;
    }

    /**
     * Updates the attributes for the user found in the database.
     * @param user User found in the database.
     * @param name Up-to-date name from auth microservice.
     * @param accessToken Up-to-date access token from auth microservice.
     */
    private void updateUserWithNewDetails(User user, String name, String accessToken) {
        boolean accessTokenChanged = !user.getAccessToken().equals(accessToken);
        boolean nameChanged = !user.getName().equals(name);

        if (accessTokenChanged || nameChanged) {
            user.setAccessToken(accessToken);
            user.setName(name);
            userRepo.save(user);
        }
    }

    /**
     * Creates a user for the user details provided by the auth microservice.
     * @param authUserId User identifier provided by the auth microservice.
     * @param name Up-to-date name from auth microservice.
     * @param accessToken Up-to-date access token from auth microservice.
     * @return Newly created user from the database.
     */
    private User createNewUserAndAccount(long authUserId, String name, String accessToken) {
        User newUser = new User(authUserId, name, accessToken);
        userRepo.save(newUser);

        Account newAccount = Account.newUserAccount();
        newAccount.setOwner(newUser);
        accountRepo.save(newAccount);

        return newUser;
    }
}
