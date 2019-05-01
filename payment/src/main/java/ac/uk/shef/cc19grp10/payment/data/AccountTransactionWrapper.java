package ac.uk.shef.cc19grp10.payment.data;

import java.time.LocalDateTime;

public class AccountTransactionWrapper {

    private Account account;
    private Transaction transaction;

    public AccountTransactionWrapper(Account account, Transaction transaction) throws AccountTransactionMismatchError {
        long accountId = account.getId();

        this.transaction = transaction;

        if (accountId != transaction.getToAccount().getId() && accountId != transaction.getFromAccount().getId()) {
            throw new AccountTransactionMismatchError();
        }

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Account getOtherAccount() {
        long accountId = getAccount().getId();

        if (transaction.getFromAccount().getId() == accountId) {
            return transaction.getFromAccount();
        } else {
            return transaction.getToAccount();
        }
    }

    public int getAmount() {
        return transaction.getAmount();
    }

    public Account getFromAccount() {
        return transaction.getFromAccount();
    }

    public Account getToAccount() {
        return transaction.getToAccount();
    }

    public boolean isCredit() {
        return account.getId() == transaction.getToAccount().getId();
    }

    public boolean isDebit() {
        return !isCredit();
    }

    public LocalDateTime getCreatedAt() {
        return transaction.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return transaction.getUpdatedAt();
    }

    public class AccountTransactionMismatchError extends Exception {}
}