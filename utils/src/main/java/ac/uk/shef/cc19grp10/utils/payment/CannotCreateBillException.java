package ac.uk.shef.cc19grp10.utils.payment;

public class CannotCreateBillException extends Exception {
    public CannotCreateBillException() {}

    public CannotCreateBillException(String msg) {
        super(msg);
    }

    public CannotCreateBillException(Throwable cause) {
        super(cause);
    }

    public CannotCreateBillException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
