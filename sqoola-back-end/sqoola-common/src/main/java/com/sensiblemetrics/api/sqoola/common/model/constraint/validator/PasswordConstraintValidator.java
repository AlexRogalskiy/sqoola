package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.google.common.base.Joiner;
import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.ValidPassword;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(final ValidPassword arg0) {
    }

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
            // at least 8 characters
            new LengthRule(8, 30),
            // at least one upper-case character
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            // at least one lower-case character
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            // at least one digit character
            new CharacterRule(EnglishCharacterData.Digit, 1),
            // at least one symbol (special character)
            new CharacterRule(EnglishCharacterData.Special, 1),
            // no whitespace
            new WhitespaceRule()

        ));

        final RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(Joiner.on(",")
            .join(validator.getMessages(result)))
            .addConstraintViolation();
        return false;
    }
}
