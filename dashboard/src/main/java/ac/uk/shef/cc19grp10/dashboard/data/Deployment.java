package ac.uk.shef.cc19grp10.dashboard.data;

import javax.persistence.*;

/**
 * <Doc here>
 * <p>
 * Created on 21/04/2019.
 */
@Entity
public class Deployment {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String imagePath;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE}, optional = false)
	private Application application;

	public Deployment() {}

	public Deployment(String url, String imagePath, Application application) {
		this.url = url;
		this.imagePath = imagePath;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
}
