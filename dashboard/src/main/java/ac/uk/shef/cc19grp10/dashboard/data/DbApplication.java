package ac.uk.shef.cc19grp10.dashboard.data;

import javax.persistence.*;

/**
 * <Doc here>
 * <p>
 * Created on 21/04/2019.
 */
@Entity
public class DbApplication {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String dbUsername;
	@Column(unique = true)
	private String schemaName;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private Application application;

	protected DbApplication(){}

	public DbApplication(String dbUsername, String schemaName, Application application){
		this.dbUsername = dbUsername;
		this.schemaName = schemaName;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getDbUrl() {
		return "jdbc:mysql://localhost/"+schemaName+"?useUnicode=true&characterEncoding=UTF-8";
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
}
