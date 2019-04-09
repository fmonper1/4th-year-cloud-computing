package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.SecureUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;

/**
 * <Doc here>
 * <p>
 * Created on 09/04/2019.
 */
@Entity
@IdClass(AuthorisationId.class)
public class Authorisation {
	@Lob
	private byte[] authCode;

	@Id
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="userId", referencedColumnName="Id")
	private User user;

	@Id
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="applicationId", referencedColumnName="Id")
	private Application application;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private AccessToken accessToken;

	public Authorisation(){}

	public Authorisation(User user, Application application) {
		this.user = user;
		this.application = application;
		this.authCode = SecureUtils.randomBytes(32);
	}

	public byte[] getAuthCode() {
		return authCode;
	}

	public String getAuthCodeEncoded() {
		return Base64.getUrlEncoder().encodeToString(authCode);
	}

	public boolean checkAuthCodeEncoded(String authCodeEncoded) {
		return SecureUtils.bytesEqual(authCode, Base64.getUrlDecoder().decode(authCodeEncoded));
	}

	public Application getApplication() {
		return application;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public User getUser() {
		return user;
	}
}

class AuthorisationId implements Serializable {
	private long user;
	private long application;

	public int hashCode() {
		return (int)(user + application);
	}

	public boolean equals(Object object) {
		if (object instanceof AuthorisationId) {
			AuthorisationId otherId = (AuthorisationId) object;
			return (user == otherId.user) && (application == otherId.application);
		}
		return false;
	}
}