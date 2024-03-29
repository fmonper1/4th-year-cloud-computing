package ac.uk.shef.cc19grp10.bookswap.models;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Listing") // This tells Hibernate to make a table out of this class
@Table(name = "LISTING")
public class Listing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
	private String description;

	private Boolean closed;


	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	private String moduleCode;

	@CreationTimestamp
	private Date createDateTime;

	@UpdateTimestamp
	private Date updateDateTime;

	public Listing(){}

	public Listing(String title, String description, String moduleCode, User user){
		this.title = title;
		this.description = description;
		this.moduleCode = moduleCode;
		this.user = user;
		this.closed = Boolean.FALSE;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}


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

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

}