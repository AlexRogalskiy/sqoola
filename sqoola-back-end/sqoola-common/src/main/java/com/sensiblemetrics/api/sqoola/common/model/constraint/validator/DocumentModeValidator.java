package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.DocumentMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link DocumentMode} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */

/**
 * @DocumentMode(groups = Health.class)
 * private Documents healthDocuments;
 * @DocumentMode(groups = Professional.class)
 * private Documents ProfessionalDocuments;
 */
public class DocumentModeValidator implements ConstraintValidator<DocumentMode, String> {

    /**
     * Default max range
     */
    private String mode;

    @Override
    public void initialize(final DocumentMode constraintAnnotation) {
        this.mode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        boolean isValid = Objects.nonNull(value) && value.contains(this.mode);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect mode={%s} (expected value={%s})", value, this.mode)).addConstraintViolation();
        }
        return true;
    }
}

