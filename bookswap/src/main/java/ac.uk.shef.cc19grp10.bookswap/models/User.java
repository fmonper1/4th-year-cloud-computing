package ac.uk.shef.cc19grp10.bookswap.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "User") // This tells Hibernate to make a table out of this class
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    private String name;

	private String email;
	private String phoneNumber;

    public User() {};

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private List<Listing> listings;

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
}