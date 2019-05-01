package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Bill extends BaseEntity {

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account toAccount;

    private int amount;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private Transaction transaction;

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

    @JsonProperty("paid")
    public boolean isPaid() {
        return this.transaction != null;
    }

    public Bill(Account toAccount, int amount) {
        this.toAccount = toAccount;
        this.amount = amount;
    }

    protected Bill() {}
}