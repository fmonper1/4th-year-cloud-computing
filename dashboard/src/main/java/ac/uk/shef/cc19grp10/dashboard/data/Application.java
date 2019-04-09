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

	protected Application(){}

	public Application(String name, String url, String imagePath,String description){
		this.name = name;
		this.url = url;
		this.imagePath = imagePath;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}
}