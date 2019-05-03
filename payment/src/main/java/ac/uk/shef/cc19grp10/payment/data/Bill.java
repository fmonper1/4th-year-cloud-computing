package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Represents an bill requesting a transaction into one account in the database.
 */

@Entity
public class Bill extends BaseEntity {

    /* Entity attributes */

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account toAccount;

    private int amount;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private Transaction transaction;

    /* Getters */

    public Account getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    /* Setters */

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /* Other Methods */

    /**
     * Determines if the bill has been paid by observing if a related transaction exists.
     * @return Paid or not.
     */
    @JsonProperty("paid")
    public boolean isPaid() {
        return this.transaction != null;
    }

    /* Constructors */

    /**
     * Initializes a new bill which requests to deposit a certain amount into a specified account.
     * @param toAccount Account to deposit funds into.
     * @param amount The amount in which to deposit.
     */
    public Bill(Account toAccount, int amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    /**
     * Empty constructor for use with JPA.
     */
    protected Bill() {}
}