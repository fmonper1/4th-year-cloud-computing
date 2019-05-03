package ac.uk.shef.cc19grp10.payment.services;

import ac.uk.shef.cc19grp10.payment.data.Account;
import ac.uk.shef.cc19grp10.payment.data.Bill;
import ac.uk.shef.cc19grp10.payment.data.Transaction;

public interface TransactionManagement {
    Transaction createTransactionFromBill(Bill bill, Account fromAccount) throws InsufficientFundsError;

    class InsufficientFundsError extends Throwable {
    }
}
