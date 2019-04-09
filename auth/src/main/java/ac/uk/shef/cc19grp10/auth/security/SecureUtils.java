package ac.uk.shef.cc19grp10.auth.security;

import java.security.MessageDigest;
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

	public static boolean bytesEqual(byte[] a, byte[] b) {
		//MessageDigest.isEqual does constant time comparison
		//not strictly necessary, but good to be cautious
		return MessageDigest.isEqual(a,b);
	}
}
