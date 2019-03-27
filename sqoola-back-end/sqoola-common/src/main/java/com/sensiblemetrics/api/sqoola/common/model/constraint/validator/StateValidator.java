package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link State} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class StateValidator implements ConstraintValidator<State, String> {

    /**
     * Default state value
     */
    private String state;

    @Override
    public void initialize(final State constraintAnnotation) {
        this.state = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        boolean isValid = value.equalsIgnoreCase(this.state);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect state={%s} (expected value={%s})", value, this.state)).addConstraintViolation();
        }
        return isValid;
    }
}
