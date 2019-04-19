package hello.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User") // This tells Hibernate to make a table out of this class
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String name;

    private String email;

    public User() {};

	public User(String name, String email) {
		this.name = name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Listing> listings;

	public Set<Listing> getListings() {
		return listings;
	}

	public void setListings(Set<Listing> listings) {
		this.listings = listings;
	}
}