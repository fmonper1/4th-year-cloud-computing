package ac.uk.shef.cc19grp10.tutorFinder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private long authId;
    @Column(nullable = false)
    private String accessToken;


    protected User(){}

    public User(long authId, String accessToken){
        this.authId = authId;
        this.accessToken = accessToken;
    }

    public long getId() {
        return id;
    }

    public long getAuthId() {
        return authId;
    }

    public void setAuthId(long authId) {
        this.authId = authId;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}