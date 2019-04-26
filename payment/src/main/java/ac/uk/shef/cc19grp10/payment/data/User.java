package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private Long id; // From auth microservice

    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String accessToken;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner", targetEntity = Account.class)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Account account;

    public Long getId() {
        return id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User(long id, String name, String accessToken) {
        this.id = id;
        this.name = name;
        this.accessToken = accessToken;
    }

    protected User() {
    }
}