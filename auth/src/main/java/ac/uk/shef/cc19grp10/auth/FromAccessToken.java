package ac.uk.shef.cc19grp10.auth;

import java.lang.annotation.*;

/**
 * <Doc here>
 * <p>
 * Created on 18/04/2019.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FromAccessToken {
}
