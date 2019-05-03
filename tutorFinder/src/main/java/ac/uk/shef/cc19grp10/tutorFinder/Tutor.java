package ac.uk.shef.cc19grp10.tutorFinder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "Tutor")
@Table(name = "TUTOR")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String emailAddress;

    @NotNull
    private String bio;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "tutor_subjects",
            joinColumns = { @JoinColumn(name = "tutor_id") },
            inverseJoinColumns = { @JoinColumn(name = "subject_id") })
    private Set<Subject> subjects = new HashSet<>();


    public Tutor() {

    }


    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }


    public Tutor(@NotNull String firstName, @NotNull String lastName, @NotNull String emailAddress, @NotNull String address, @NotNull String phoneNumber, @NotNull String bio, Set<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.subjects = subjects;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Subject> getSubjects(){
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects){
        this.subjects = subjects;
    }
}



