package ac.uk.shef.cc19grp10.utils.payment;

public class CannotVerifyBillException extends Exception {
    public CannotVerifyBillException() {}

    public CannotVerifyBillException(String msg) {
        super(msg);
    }

    public CannotVerifyBillException(Throwable cause) {
        super(cause);
    }

    public CannotVerifyBillException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
