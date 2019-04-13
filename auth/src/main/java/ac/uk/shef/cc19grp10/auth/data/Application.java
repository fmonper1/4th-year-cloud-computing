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
    @Column(unique = true)
    private String name;
	@Column(unique = true)
	private String clientId;
	@Column(unique = true)
	private String dbUsername;
	@Column(unique = true)
	private String schemaName;
	private String redirectUri;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private User owner;

	@Lob
	private byte[] clientSecret;

	@OneToMany(mappedBy="application")
	private List<Authorisation> authorisations;

	protected Application(){}

	public Application(String name, String clientId, String dbUsername, String schemaName, User owner, String redirectUri){
		this.name = name;
		this.clientId = clientId;
		this.dbUsername = dbUsername;
		this.schemaName = schemaName;
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

	public String getDbUsername() {
		return dbUsername;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getDbUrl() {
		return "jdbc:mysql://localhost/"+schemaName+"?useUnicode=true&characterEncoding=UTF-8";
	}
}