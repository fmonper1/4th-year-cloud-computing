package ac.uk.shef.cc19grp10.dashboard.data;

import ac.uk.shef.cc19grp10.dashboard.utils.NameUtils;

import javax.persistence.*;

@Entity
public class Application {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String name;
	private String description;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private User owner;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, orphanRemoval = true, mappedBy = "application")
	private Deployment deployment;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, orphanRemoval = true, mappedBy = "application")
	private DbApplication dbApplication;

	protected Application(){}

	public Application(String name,String description, User owner){
		this.name = name;
		this.description = description;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
  
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Deployment getDeployment() {
		return deployment;
	}

	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}

	public DbApplication getDbApplication() {
		return dbApplication;
	}

	public void setDbApplication(DbApplication dbApplication) {
		this.dbApplication = dbApplication;
	}

	public String getUrl(){
		return NameUtils.nameToUrl(this.name);
	}

	public String getClientId() {
		return NameUtils.nameToClientId(this.name);
	}

	public String getSchemaName() {
		return NameUtils.nameToSchemaName(this.name);
	}
}