package ac.uk.shef.cc19grp10.bookswap.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Comment")
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne
    @JoinColumn(name="listing_id", nullable=false)
    private Listing listing;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    //Constructors, getters and setters removed for brevity

    public Comment() {}

    public Comment(String comment, User user, Listing listing) {
        this.comment = comment;
        this.user = user;
        this.listing = listing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment )) return false;
        return id != null && id.equals(((Comment) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}