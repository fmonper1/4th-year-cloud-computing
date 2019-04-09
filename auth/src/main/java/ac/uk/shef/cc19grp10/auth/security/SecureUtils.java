package ac.uk.shef.cc19grp10.auth.security;

import java.security.SecureRandom;

/**
 * <Doc here>
 * <p>
 * Created on 09/04/2019.
 */
public class SecureUtils {
	private static SecureRandom csprng = new SecureRandom();

	public static byte[] randomBytes(int length){
		byte[] salt = new byte[length];
		csprng.nextBytes(salt);
		return salt;
	}
}
