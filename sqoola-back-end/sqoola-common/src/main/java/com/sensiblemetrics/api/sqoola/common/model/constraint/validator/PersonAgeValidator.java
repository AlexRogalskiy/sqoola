package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.PersonAge;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link PersonAge} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class PersonAgeValidator implements ConstraintValidator<PersonAge, Integer> {

    @Override
    public void initialize(final PersonAge constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Integer age, final ConstraintValidatorContext context) {
        if (Objects.isNull(age)) {
            return true;
        }
        boolean isValid = age > 0;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect age={%s} (should be greater than zero)", age)).addConstraintViolation();
        }
        return true;
    }
}
