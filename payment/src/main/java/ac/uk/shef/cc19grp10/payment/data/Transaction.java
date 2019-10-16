package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a transaction between two accounts in the database.
 */

@Entity
public class Transaction extends BaseEntity implements Comparable<Transaction> {

    /* Entity attributes */

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account fromAccount;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account toAccount;

    private int amount;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transaction", targetEntity = Bill.class)
    private Bill bill;

    /* Getters */

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    /* Constructors */

    /**
     * Initializes a transaction entity with a debit account, credit account, and amount.
     * @param from Account amount is withdrawn from.
     * @param to Account amount is deposited into.
     * @param amount Amount transferred.
     */
    public Transaction(Account from, Account to, int amount) {
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;
    }

    /**
     * Blank constructor for JPA.
     */
    protected Transaction() {}

    /* Comparable<Transaction> interface methods */

    /**
     * Compares this transaction with another transaction.
     * @param otherTransaction The other transaction.
     * @return 0 if they were created simultaneously, -1 if the current transaction was created before, +1 otherwise.
     */
    @Override
    public int compareTo(Transaction otherTransaction) {
        LocalDateTime thisCreatedAt = getCreatedAt();
        LocalDateTime otherCreatedAt = otherTransaction.getCreatedAt();

        if (thisCreatedAt.equals(otherCreatedAt)) {
            return (int) (getId() - otherTransaction.getId());
        }

        if (thisCreatedAt.isBefore(otherCreatedAt)) {
            return -1;
        } else {
            return 1;
        }
    }
}