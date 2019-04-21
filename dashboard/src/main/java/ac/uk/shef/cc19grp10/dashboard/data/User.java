package ac.uk.shef.cc19grp10.dashboard.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * <Doc here>
 * <p>
 * Created on 19/04/2019.
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true)
	private long authId;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String accessToken;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "owner")
	private Application application;

	protected User(){}

	public User(long authId, String name, String accessToken){
		this.authId = authId;
		this.name = name;
		this.accessToken = accessToken;
	}

	public long getId() {
		return id;
	}

	public long getAuthId() {
		return authId;
	}

	public void setAuthId(long authId) {
		this.authId = authId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Application getApplication() {
		return application;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
