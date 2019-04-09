package ac.uk.shef.cc19grp10.auth.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * <Doc here>
 * <p>
 * Created on 08/04/2019.
 */
public class PBKDF2HashingStrategy implements HashingStrategy {

	@Override
	public byte[] generateSalt(){
		//NIST Reccomends 128 bits of salt for PBKDF2 (https://nvlpubs.nist.gov/nistpubs/Legacy/SP/nistspecialpublication800-132.pdf#page=10)
		return SecureUtils.randomBytes(128/8);
	}

	@Override
	public byte[] hash(String password, byte[] salt) {
		try {
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec ks = new PBEKeySpec(password.toCharArray(), salt, 1024, 128);
			SecretKey s = f.generateSecret(ks);
			return s.getEncoded();
		} catch (NoSuchAlgorithmException e) {
			//TODO: better error handling
			throw new RuntimeException("Configuration error: Selected hash algorithm not available");
		} catch (InvalidKeySpecException e) {
			//TODO: better error handling
			throw new RuntimeException("Configuration error: PBEKeySpec unsuitable for selected hash algorithm");
		}
	}
}
