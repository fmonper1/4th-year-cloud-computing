package hello.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "listings")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "fk_creator")
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Listing(){}

	public Listing(String title, String description){
		this.title = title;
		this.description = description;
	}

}