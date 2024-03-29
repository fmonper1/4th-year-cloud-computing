package ac.uk.shef.cc19grp10.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <Doc here>
 * <p>
 * Created on 10/04/2019.
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldsMatchValidator.class)
@Documented
public @interface FieldsMatch {
	String[] value();
	String message() default "{ac.uk.shef.cc19grp10.utils.validation.FieldsMatch.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
