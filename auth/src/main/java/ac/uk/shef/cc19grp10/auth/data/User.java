package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;

import javax.persistence.*;
import java.security.MessageDigest;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private byte[] salt;
	@Lob
	private byte[] pwHash;

	protected User(){}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte[] getSalt() {
		return salt;
	}

	public byte[] getPwHash() {
		return pwHash;
	}

	public User(String name, String password, HashingStrategy hashingStrategy){
		this.name = name;
		this.salt = hashingStrategy.generateSalt();
		this.pwHash = hashingStrategy.hash(password,this.salt);
	}

	public boolean passwordMatches(String password, HashingStrategy hashingStrategy){
		byte[] inputHash = hashingStrategy.hash(password,this.salt);
		//MessageDigest.isEqual does constant time comparison
		//not strictly necessary, but good to be cautious
		return MessageDigest.isEqual(inputHash,this.pwHash);
	}

}