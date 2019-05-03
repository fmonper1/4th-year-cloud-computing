package ac.uk.shef.cc19grp10.payment.data;

import java.time.LocalDateTime;

/**
 * Wraps an Account and Transaction together to provide helpful methods for JSP views.
 */
public class AccountTransactionWrapper implements Comparable<AccountTransactionWrapper> {

    private Account account;
    private Transaction transaction;

    /**
     * Initializes the wrapper with the two objects.
     * @param account Account to which the transaction belongs.
     * @param transaction The transaction which the utility methods should act on.
     * @throws AccountTransactionMismatchError Thrown if the transaction does not belong to the user. Methods would not
     *                                         work in this case.
     */
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

    /**
     * Gets the account which is the counterpart to the account in the wrapper. This could be either the sender or receiver.
     * @return Other account in transaction.
     */
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

    /**
     * Determines if the transaction was a credit with respect to the account.
     * @return If the transaction credited the account.
     */
    public boolean isCredit() {
        return account.getId() == transaction.getToAccount().getId();
    }

    /**
     * Determines if the transaction was a debit with respect to the account.
     * @return If the transaction debited the account.
     */
    public boolean isDebit() {
        return !isCredit();
    }

    public LocalDateTime getCreatedAt() {
        return transaction.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return transaction.getUpdatedAt();
    }

    /**
     * Compare two transaction wrappers using their wrapped transaction.
     * @param otherTransactionWrapper Other transaction wrapper
     * @return Output of delegation to Transaction.compareTo
     */
    @Override
    public int compareTo(AccountTransactionWrapper otherTransactionWrapper) {
        return transaction.compareTo(otherTransactionWrapper.transaction);
    }

    public class AccountTransactionMismatchError extends Exception {}
}