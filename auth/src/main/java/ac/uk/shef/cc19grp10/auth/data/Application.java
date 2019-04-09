package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;
import ac.uk.shef.cc19grp10.auth.security.SecureUtils;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
	private String clientId;

	@Lob
	private byte[] clientSecret;

	@OneToMany(mappedBy="application")
	private List<Authorisation> authorisations;

	protected Application(){}

	public Application(String name, String clientId){
		this.name = name;
		this.clientId = clientId;
		this.clientSecret = SecureUtils.randomBytes(32);
	}

	public String getName() {
		return name;
	}

	public String getClientId() {
		return clientId;
	}

	public long getId() {
		return id;
	}

	public boolean checkEncodedClientSecret(String encodedClientSecret) {
		return SecureUtils.bytesEqual(clientSecret, Base64.getUrlDecoder().decode(encodedClientSecret));
	}

	public String getEncodedClientSecret() {
		return Base64.getUrlEncoder().encodeToString(clientSecret);
	}
}