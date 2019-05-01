package ac.uk.shef.cc19grp10.payment.services;

import ac.uk.shef.cc19grp10.payment.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class TransactionManagementImpl implements TransactionManagement {

    private static long AUTH_SERVICE_ACCT_ID = 1;
    private static long PAYMENT_SERVICE_ACCT_ID = 2;

    private Logger logger = LoggerFactory.getLogger(TransactionManagementImpl.class);

    @Value("${payment.platform_cut}")
    private float PLATFORM_CUT;

    private final TransactionRepository transactionRepo;
    private final AccountRepository accountRepo;
    private final BillRepository billRepo;

    public TransactionManagementImpl(TransactionRepository transactionRepo, AccountRepository accountRepo, BillRepository billRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.billRepo = billRepo;
    }

    public Transaction createTransactionFromBill(Bill bill, Account fromAccount) throws InsufficientFundsError {
        logger.info("bill = {}", bill);
        logger.info("fromAccount = {} with id {}", fromAccount, fromAccount.getId());
        if (fromAccount.getBalance() < bill.getAmount()) {
            throw new InsufficientFundsError();
        }

        Account authServiceAccount = accountRepo.findById(AUTH_SERVICE_ACCT_ID)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        Account paymentServiceAccount = accountRepo.findById(PAYMENT_SERVICE_ACCT_ID)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        logger.info("Balance sufficient");

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
}
