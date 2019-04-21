package ac.uk.shef.cc19grp10.dashboard.data;

import javax.persistence.*;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
	private String url;
	private String imagePath;
	private String description;
	@Column(unique = true)
	private String dbUsername;
	@Column(unique = true)
	private String schemaName;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private User owner;

	protected Application(){}

	public Application(String name, String url, String imagePath,String description, String dbUsername, String schemaName, User owner){
		this.name = name;
		this.url = url;
		this.imagePath = imagePath;
		this.description = description;
		this.dbUsername = dbUsername;
		this.schemaName = schemaName;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDbUrl() {
		return "jdbc:mysql://localhost/"+schemaName+"?useUnicode=true&characterEncoding=UTF-8";
	}
}