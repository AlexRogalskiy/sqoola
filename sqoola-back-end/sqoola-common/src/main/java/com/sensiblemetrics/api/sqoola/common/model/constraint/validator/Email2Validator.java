package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.Email2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link Email2} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class Email2Validator implements ConstraintValidator<Email2, String> {
    /**
     * Default mail pattern
     */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    public void initialize(final Email2 constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        boolean isValid = this.validateEmail(email);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect email={%d} (expected pattern {%s})", email, EMAIL_PATTERN)).addConstraintViolation();
        }
        return true;
    }

    private boolean validateEmail(final String email) {
        final Matcher matcher = this.pattern.matcher(email);
        return matcher.matches();
    }
}
