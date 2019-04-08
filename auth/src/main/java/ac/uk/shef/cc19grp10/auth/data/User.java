package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;

import javax.persistence.*;
import java.security.MessageDigest;

@Entity
public class User {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @Lob
    private byte[] salt;
	@Lob
	private byte[] pw_hash;

	public boolean passwordMatches(String password, HashingStrategy hashingStrategy){
		byte[] inputHash = hashingStrategy.hash(password,this.salt);
		//MessageDigest.isEqual does constant time comparison
		//not strictly necessary, but good to be cautious
		return MessageDigest.isEqual(inputHash,this.pw_hash);
	}

}