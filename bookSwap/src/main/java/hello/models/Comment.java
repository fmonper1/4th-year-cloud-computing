package hello.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Comment")
@Table(name = "comments")
public class Comment {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String review;

    @ManyToOne
    @JoinColumn(name="listing_id", nullable=false)
    private Listing listing;

    //Constructors, getters and setters removed for brevity

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
}