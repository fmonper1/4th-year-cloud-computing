
package ac.uk.shef.cc19grp10.tutorFinder;

import ac.uk.shef.cc19grp10.utils.login.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
public class DbUserFactory implements UserFactory {

    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public Object loadOrCreateUser(long id, String name, String accessToken) {
        User user = userRepo.findByAuthId(id).orElseGet(() -> createUser(id, name, accessToken));
        user.setAccessToken(accessToken);
        return userRepo.save(user);
    }

    private User createUser(long id, String name, String accessToken) {
        try{
            return userRepo.save(new User(id, accessToken));
        }catch(EntityExistsException e){
            //Assuming a race created it, so just find and return it.
            return userRepo.findByAuthId(id).get();
        }
    }
}