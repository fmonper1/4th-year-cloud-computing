package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.SecureUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.Objects;

/**
 * <Doc here>
 * <p>
 * Created on 09/04/2019.
 */
@Entity
public class Authorisation {

	@EmbeddedId
	private AuthorisationId id;

	@Lob
	private byte[] authCode;

	@MapsId("userId")
	@ManyToOne(optional = false)
	@JoinColumn(name="userId", referencedColumnName="Id")
	private User user;

	@MapsId("applicationId")
	@ManyToOne(optional = false)
	@JoinColumn(name="applicationId", referencedColumnName="Id")
	private Application application;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private AccessToken accessToken;

	public Authorisation(){
		id = new AuthorisationId();
	}

	public Authorisation(User user, Application application) {
		this.user = user;
		this.application = application;
		this.authCode = SecureUtils.randomBytes(32);
		this.id = new AuthorisationId(user.getId(),application.getId());
	}

	public String getAuthCodeEncoded() {
		return Base64.getUrlEncoder().encodeToString(authCode);
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

	public AuthorisationId getId() {
		return id;
	}

	public void setId(AuthorisationId id) {
		this.id = id;
	}
}

@Embeddable
class AuthorisationId implements Serializable {
	@Column(name="userId")
	private Long userId;
	@Column(name="applicationId")
	private Long applicationId;

	public AuthorisationId() {

	}

	public AuthorisationId(long userId, long applicationId) {
		this.userId = userId;
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorisationId that = (AuthorisationId) o;
		return Objects.equals(userId, that.userId) &&
				Objects.equals(applicationId, that.applicationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, applicationId);
	}
}