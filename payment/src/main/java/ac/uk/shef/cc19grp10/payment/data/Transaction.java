package ac.uk.shef.cc19grp10.payment.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account fromAccount;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private Account toAccount;

    private int amount;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "transaction", targetEntity = Bill.class)
    private Bill bill;

    public Long getId() {
        return id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Transaction(Account from, Account to, int amount) {
        this.fromAccount = from;
        this.toAccount = to;
        this.amount = amount;
    }

    protected Transaction() {}
}