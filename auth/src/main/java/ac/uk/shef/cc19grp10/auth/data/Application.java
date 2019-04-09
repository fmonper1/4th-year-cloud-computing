package ac.uk.shef.cc19grp10.auth.data;

import ac.uk.shef.cc19grp10.auth.security.HashingStrategy;

import javax.persistence.*;
import java.security.MessageDigest;
import java.util.List;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
	private String clientId;

	@OneToMany(mappedBy="application")
	private List<Authorisation> authorisations;

	protected Application(){}

	public Application(String name, String clientId){
		this.name = name;
		this.clientId = clientId;
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
}