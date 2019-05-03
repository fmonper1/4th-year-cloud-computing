package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an peanut account in the database. Usually belongs to a user.
 */

@Entity
public class Account extends BaseEntity {

    @JsonIgnore
    private static final int INITIAL_USER_BALANCE = 1000;

    /* Entity attributes */

    private int balance;

    @OneToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fromAccount", targetEntity = Transaction.class)
    private Set<Transaction> outgoingTransactions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "toAccount", targetEntity = Transaction.class)
    private Set<Transaction> incomingTransactions;

    /* Instance methods */

    public int getBalance() {
        return balance;
    }

    /**
     * Increments the balance of the account by a given amount.
     * @param amount Quantity to increase balance by.
     */
    public void incrementBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Decrements the balance of the account by a given amount.
     * @param amount Quantity to decrease balance by.
     */
    public void decrementBalance(int amount) {
        this.balance -= amount;
    }

    /**
     * Moves balance from one account to another.
     * @param from Account to decrement the balance from.
     * @param to Account to increment the balance to.
     * @param amount Amount of balance to transfer.
     */
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

    /**
     * Combines the outgoing and incoming transactions into one set.
     * @return All transactions for the account
     */
    @JsonIgnore
    public Set<Transaction> getTransactions() {
        Set<Transaction> allTransactions = new HashSet<>();
        allTransactions.addAll(outgoingTransactions);
        allTransactions.addAll(incomingTransactions);
        return allTransactions;
    }

    /**
     * Initialises an account entity with the given initial balance.
     * @param initialBalance Balance to initialize with.
     */
    private Account(int initialBalance) {
        this();
        this.balance = initialBalance;
    }

    /**
     * Constructor with no args for use with JPA.
     */
    protected Account() {
        this.incomingTransactions = new HashSet<>();
        this.outgoingTransactions = new HashSet<>();
    }

    /**
     * Initializes a new Account entity with the initial balance for a user.
     * @return Default account for a user.
     */
    public static Account newUserAccount() {
        // A similar method could be implemented if we wanted to start differentiating applications from users.
        return new Account(INITIAL_USER_BALANCE);
    }
}