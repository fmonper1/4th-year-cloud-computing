package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.SecureUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Base64;
import java.util.Date;

/**
 * <Doc here>
 * <p>
 * Created on 09/04/2019.
 */
@Entity
public class AccessToken {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date expires;
	@Lob
	@JsonIgnore
	private byte[] accessToken;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "accessToken")
	private Authorisation authorisation;

	public AccessToken(){}

	public AccessToken(Date expires) {
		this.expires = expires;
		this.accessToken = SecureUtils.randomBytes(32);
	}

	public Date getExpires() {
		return expires;
	}

	public boolean isExpired() {
		return expires.before(new Date());
	}

	public byte[] getAccessToken() {
		return accessToken;
	}

	@JsonProperty("access_token")
	public String getAccessTokenEncoded() {
		return Base64.getUrlEncoder().encodeToString(accessToken);
	}

	public boolean checkAccessTokenEncoded(String authCodeEncoded) {
		return SecureUtils.bytesEqual(accessToken, Base64.getUrlDecoder().decode(authCodeEncoded));
	}

	@JsonProperty("expires_in")
	public long getExpiresIn(){
		long expiry = expires.getTime() - new Date().getTime();
		return expiry < 0 ? 0 : expiry ;
	}
}
