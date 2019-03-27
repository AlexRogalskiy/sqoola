package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.google.common.base.Joiner;
import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.Password;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * {@link Password} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(final Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final org.passay.PasswordValidator validator = new org.passay.PasswordValidator(Arrays.asList(
            new LengthRule(8, 30),
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            new CharacterRule(EnglishCharacterData.Digit, 1),
            new CharacterRule(EnglishCharacterData.Special, 1),
            new WhitespaceRule()
        ));

        final RuleResult result = validator.validate(new PasswordData(password));
        if (!result.isValid()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect password={%s} (message={%s})", password, Joiner.on(",").join(validator.getMessages(result)))).addConstraintViolation();
        }
        return true;
    }
}
