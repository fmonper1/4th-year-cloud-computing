package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.SecureUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Base64;
import java.util.List;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
	@Column(unique = true)
	private String clientId;
	private String redirectUri;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private User owner;

	@Lob
	private byte[] clientSecret;

	@OneToMany(mappedBy="application")
	private List<Authorisation> authorisations;

	protected Application(){}

	public Application(String name, String clientId, User owner, String redirectUri){
		this.name = name;
		this.clientId = clientId;
		this.owner = owner;
		this.clientSecret = SecureUtils.randomBytes(32);
		this.redirectUri = redirectUri;
	}

	public String getName() {
		return name;
	}

	public String getClientId() {
		return clientId;
	}

	public Long getId() {
		return id;
	}

	public boolean checkEncodedClientSecret(String encodedClientSecret) {
		return SecureUtils.bytesEqual(clientSecret, Base64.getUrlDecoder().decode(encodedClientSecret));
	}

	public String getEncodedClientSecret() {
		return Base64.getUrlEncoder().encodeToString(clientSecret);
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public User getOwner() {
		return owner;
	}
}