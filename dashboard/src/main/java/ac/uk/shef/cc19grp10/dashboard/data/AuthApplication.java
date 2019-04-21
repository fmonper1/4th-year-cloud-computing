package ac.uk.shef.cc19grp10.dashboard.data;

/**
 * Application info to be got from /auth/developer/
 * <p>
 * Created on 19/04/2019.
 */
//TODO: maybe make this a superclass or interface so it can be in a shared lib shared with auth service and be kept in sync
public class AuthApplication {
	private Long id;
	private String name;
	private String clientId;
	private String dbUsername;
	private String schemaName;
	private String redirectUri;
	private String encodedClientSecret;

	protected AuthApplication(){}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClientId() {
		return clientId;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public String getEncodedClientSecret() {
		return encodedClientSecret;
	}
}
