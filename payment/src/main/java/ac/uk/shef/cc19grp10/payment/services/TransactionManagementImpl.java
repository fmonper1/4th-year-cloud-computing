package ac.uk.shef.cc19grp10.payment.services;

import ac.uk.shef.cc19grp10.payment.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Provides service methods for transaction management
 */

@Service
public class TransactionManagementImpl implements TransactionManagement {

    private static long AUTH_SERVICE_ACCT_ID = 1; // Account ID for auth service created from seed DB migration.
    private static long PAYMENT_SERVICE_ACCT_ID = 2; // Account ID for payment service created from seed DB migration.

    private Logger logger = LoggerFactory.getLogger(TransactionManagementImpl.class);

    @Value("${payment.platform_cut}")
    private float PLATFORM_CUT;

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final BillRepository billRepo;

    /**
     * Constructor for use with dependency injection.
     * @param transactionRepo Autowired TransactionRepository dep.
     * @param accountRepo Autowired AccountRepository dep.
     * @param billRepo Autowired BillRepository dep.
     */
    public TransactionManagementImpl(TransactionRepository transactionRepo, AccountRepository accountRepo, BillRepository billRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.billRepo = billRepo;
    }

    /**
     * Creates the appropriate transaction records in the database for the completed bill.
     * @param bill Bill that has been approved for payment.
     * @param fromAccount Account which funds should be withdrawn from.
     * @return Newly created transaction entity for the bill.
     * @throws InsufficientFundsError Thrown if the account being withdrawn from has an insufficient balance.
     */
    public Transaction createTransactionFromBill(Bill bill, Account fromAccount) throws InsufficientFundsError {
        logger.info("bill = {}", bill);
        logger.info("fromAccount = {} with id {}", fromAccount, fromAccount.getId());

        if (fromAccount.getBalance() < bill.getAmount()) {
            throw new InsufficientFundsError();
        }
        logger.info("Balance sufficient");

        Account authServiceAccount = getAuthServiceAccount();
        Account paymentServiceAccount = getPaymentServiceAccount();

        int platformCut = (int) Math.floor(bill.getAmount() * PLATFORM_CUT);
        int authServiceCut = platformCut / 2;
        int paymentServiceCut = platformCut - authServiceCut;

        // Make payment through to application
        Transaction mainTransaction = createTransaction(fromAccount, bill.getToAccount(), bill.getAmount());
        // Create transaction for authentication service
        createTransaction(bill.getToAccount(), authServiceAccount, authServiceCut);
        // Create transaction for payment service
        createTransaction(bill.getToAccount(), paymentServiceAccount, paymentServiceCut);

        bill.setTransaction(mainTransaction);
        billRepo.save(bill);

        return mainTransaction;
    }

    /**
     * Creates a transaction between two accounts.
     * @param fromAccount Account to withdraw from.
     * @param toAccount Account to deposit into.
     * @param amount Amount to transfer.
     * @return Newly created transaction entity.
     * @throws InsufficientFundsError Thrown if the withdrawal account does not have enough funds.
     */
    public Transaction createTransaction(Account fromAccount, Account toAccount, int amount) throws InsufficientFundsError {
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsError();
        }

        Transaction paymentTransaction = new Transaction(fromAccount, toAccount, amount);
        Account.transfer(fromAccount, toAccount, amount);

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        transactionRepo.save(paymentTransaction);

        return paymentTransaction;
    }

    /**
     * Gets the account for the authorization service.
     * @return Authorization service account.
     * @throws HttpClientErrorException If the account could not be found, something has gone rather wrong.
     */
    private Account getAuthServiceAccount() throws HttpClientErrorException {
        return accountRepo.findById(AUTH_SERVICE_ACCT_ID)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
    }


    /**
     * Gets the account for the payment service.
     * @return Payment service account.
     * @throws HttpClientErrorException If the account could not be found, something has gone rather wrong.
     */
    private Account getPaymentServiceAccount() throws HttpClientErrorException {
        return accountRepo.findById(PAYMENT_SERVICE_ACCT_ID)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
