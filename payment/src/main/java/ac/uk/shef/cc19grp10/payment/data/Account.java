package ac.uk.shef.cc19grp10.payment.data;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int balance;

    protected Account() {
        // Nothing special here
    }

    public Long getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public Account(int initialBalance){
        this.balance = initialBalance;
    }
}