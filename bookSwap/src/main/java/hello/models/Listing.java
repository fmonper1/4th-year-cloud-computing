package hello.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Listing") // This tells Hibernate to make a table out of this class
@Table(name = "LISTING")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

	private String description;

	@OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)

	private List<Comment> comments = new ArrayList<>();



	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
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


	public void setUser(User user) {
		this.user = user;
	}
}