package ac.uk.shef.cc19grp10.utils.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <Doc here>
 * <p>
 * Created on 10/04/2019.
 */
public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {
	private String[] fields;
	private static Logger logger = LoggerFactory.getLogger(FieldsMatchValidator.class);

	@Override
	public void initialize(FieldsMatch constraintAnnotation) {
		fields = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		String expectedName = null;
		Optional<Object> expected = Optional.empty();
		Set<String> mismatches = new HashSet<>();
		for (String field: fields) {
			Object curr = null;
			try {
				curr = BeanUtils.getPropertyDescriptor(value.getClass(), field).getReadMethod().invoke(value);
			} catch (Exception e) {
				//convert to runtime exceptions, as we can't just pass on checked exceptions
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			if(expected.isPresent()){
				//if current object doesn't match we're done
				if(curr==null || !curr.equals(expected.get())){
					mismatches.add(expectedName);
					mismatches.add(field);
				}
			}else {
				expected = Optional.ofNullable(curr);
				expectedName = field;
			}
		}

		if (!mismatches.isEmpty()){
			String message = mismatches.stream().collect(Collectors.joining(", ", "The fields: ", " must match."));
			context.disableDefaultConstraintViolation();
			for(String field : mismatches) {
				context.buildConstraintViolationWithTemplate(message)
						.addPropertyNode(field)
						.addConstraintViolation();
			}
			logger.info("Fields mismatch on: {}",String.join(", ", fields));
			return false;
		}
		return true;
	}
}
