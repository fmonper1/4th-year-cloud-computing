package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Represents an auth user in the database. Generally used to identify the current user browsing and interact with auth.
 */

@Entity
public class User extends BaseEntity {

    /* Entity attributes */

    private long authId; // From auth microservice

    private String name; // From auth microservice

    @JsonIgnore
    @Column(nullable = false)
    private String accessToken; // From auth microservice

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", targetEntity = Account.class)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Account account;

    /* Getters and Setters */

    public long getAuthId() {
        return authId;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String newAccessToken) {
        this.accessToken = newAccessToken;
    }

    @Deprecated // Currently returns a stale account, due to the way the user is stored in the session. Needs refactor.
    public Account getAccount() {
        return account;
    }

    public long getAccountId() {
        return account.getId();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /* Constructors */

    /**
     * Initializes a user entity with attributes from the authorization microservice. Often used with the UserFactory.
     * @param authId Authorization ID from the auth microservice.
     * @param name Name from the authorization microservice.
     * @param accessToken Access token from the authorization microservice.
     */
    public User(long authId, String name, String accessToken) {
        this.authId = authId;
        this.name = name;
        this.accessToken = accessToken;
    }

    /**
     * Blank constructor for JPA.
     */
    protected User() {}
}