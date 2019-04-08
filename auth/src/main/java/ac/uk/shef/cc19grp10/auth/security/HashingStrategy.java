package ac.uk.shef.cc19grp10.auth.security;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
public interface HashingStrategy {
	public byte[] generateSalt();
	public byte[] hash(String password, byte[] salt);
}
