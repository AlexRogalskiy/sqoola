package com.sensiblemetrics.api.sqoola.common.validation.validator;

import de.pearl.pem.common.validation.model.GeneralRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * {@link GeneralRequest} validator
 */
public class GeneralRequestValidator implements Validator {

    /**
     * Default page size number
     */
    private static final int DEFAULT_MIN_PAGE_NUMBER = 1;

    @Override
    public boolean supports(final Class<?> clazz) {
        return GeneralRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "items", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "page", "field.required");
        final GeneralRequest<?> request = (GeneralRequest<?>) target;
        if (request.getPage() < DEFAULT_MIN_PAGE_NUMBER) {
            errors.rejectValue("page", "field.min.length", new Object[]{Integer.valueOf(DEFAULT_MIN_PAGE_NUMBER)}, "There must be at least one page");
        }
    }
}
