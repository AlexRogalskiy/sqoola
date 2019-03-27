package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
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
     * Default state values
     */
    private String[] states;

    @Override
    public void initialize(final State constraintAnnotation) {
        this.states = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        boolean isValid = Sets.newHashSet(this.states).contains(value);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect state={%s} (expected values={%s})", value, Joiner.on(",").join(this.states))).addConstraintViolation();
        }
        return true;
    }
}
