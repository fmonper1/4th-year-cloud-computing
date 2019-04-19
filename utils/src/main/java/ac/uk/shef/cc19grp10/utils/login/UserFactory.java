package ac.uk.shef.cc19grp10.utils.login;

/**
 * Used by the utils login system to instantiate an application specific user class
 * <p>
 * Created on 19/04/2019.
 */
public interface UserFactory {
	/**
	 * Should create a user object for the given id and name.
	 * Id is guaranteed to be unique.
	 * Whatever object type is returns from here you can then access using @SessionAttribute("User")
	 * i.e.
	 */
	Object loadOrCreateUser(long id, String name, String accessToken);
}
