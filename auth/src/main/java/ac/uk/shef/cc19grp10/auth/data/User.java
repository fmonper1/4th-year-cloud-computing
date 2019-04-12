package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import ac.uk.shef.cc19grp10.auth.security.SecureUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Lob
	@JsonIgnore
    private byte[] salt;
	@Lob
	@JsonIgnore
	private byte[] pwHash;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "owner")
	private Application application;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Authorisation> authorisations;

	protected User(){}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@JsonIgnore
	public byte[] getSalt() {
		return salt;
	}

	@JsonIgnore
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
		return SecureUtils.bytesEqual(inputHash,pwHash);
	}

	public boolean hasApplication() {
		return application != null;
	}

	public Application getApplication() {
		return application;
	}
}