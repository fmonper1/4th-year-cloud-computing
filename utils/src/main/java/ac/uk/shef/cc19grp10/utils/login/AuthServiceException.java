package ac.uk.shef.cc19grp10.utils.login;

public class AuthServiceException extends Exception {
    public AuthServiceException() {}

    public AuthServiceException(String msg) {
        super(msg);
    }

    public AuthServiceException(Throwable cause) {
        super(cause);
    }

    public AuthServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
