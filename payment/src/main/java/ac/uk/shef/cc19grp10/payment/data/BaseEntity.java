package ac.uk.shef.cc19grp10.payment.data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents an abstract entity providing functionality shared among all entities in this application.
 */
@MappedSuperclass
public abstract class BaseEntity {

    /* Attributes */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /* Getters */

    public long getId() {
        return id;
    }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
