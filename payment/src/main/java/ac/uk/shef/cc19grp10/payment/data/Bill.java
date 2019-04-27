package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account toAccount;

    private int amount;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private Transaction transaction;

    public Long getId() {
        return id;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public boolean getPaid() {
        return this.transaction != null;
    }

    private Bill(Account toAccount, int amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    protected Bill() {}
}