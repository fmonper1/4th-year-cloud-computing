package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account extends BaseEntity {

    @JsonIgnore
    private static final int INITIAL_USER_BALANCE = 1000;

    private int balance;

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fromAccount", targetEntity = Transaction.class)
    private Set<Transaction> outgoingTransactions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "toAccount", targetEntity = Transaction.class)
    private Set<Transaction> incomingTransactions;

    public int getBalance() {
        return balance;
    }

    public void incrementBalance(int amount) {
        this.balance += amount;
    }

    public void decrementBalance(int amount) {
        this.balance -= amount;
    }

    public static void transfer(Account from, Account to, int amount) {
        from.decrementBalance(amount);
        to.incrementBalance(amount);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonIgnore
    public Set<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    @JsonIgnore
    public Set<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    @JsonIgnore
    public Set<Transaction> getTransactions() {
        Set<Transaction> allTransactions = new HashSet<>();
        allTransactions.addAll(outgoingTransactions);
        allTransactions.addAll(incomingTransactions);
        return allTransactions;
    }

    private Account(int initialBalance) {
        this();
        this.balance = initialBalance;
    }

    protected Account() {
        this.incomingTransactions = new HashSet<>();
        this.outgoingTransactions = new HashSet<>();
    }

    public static Account newUserAccount() {
        // A similar method could be implemented if we wanted to start differentiating applications from users.
        return new Account(INITIAL_USER_BALANCE);
    }
}