package com.sensiblemetrics.api.sqoola.common.validation.validator;

import de.pearl.pem.common.validation.model.GeneralSearchRequest;
import de.pearl.pem.common.validation.model.OffsetPageRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * {@link GeneralSearchRequest} validator
 */
public class OffsetPageRequestValidator implements Validator {

    /**
     * Default page offset
     */
    private static final int DEFAULT_PAGE_OFFSET = 0;
    /**
     * Default page size
     */
    private static final int DEFAULT_PAGE_LIMIT = 0;

    @Override
    public boolean supports(final Class<?> clazz) {
        return OffsetPageRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "offset", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "limit", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sort", "field.required");
        final OffsetPageRequest request = (OffsetPageRequest) target;
        if (request.getOffset() < DEFAULT_PAGE_OFFSET) {
            errors.rejectValue("offset", "field.min.length", new Object[]{Integer.valueOf(DEFAULT_PAGE_OFFSET)}, "Offset must not be negative");
        }
        if (request.getPageSize() < DEFAULT_PAGE_LIMIT) {
            errors.rejectValue("limit", "field.min.length", new Object[]{Integer.valueOf(DEFAULT_PAGE_LIMIT)}, "Limit must not be negative");
        }
        if (Objects.isNull(request.getSort())) {
            errors.rejectValue("limit", "field.notNullOrEmpty", null, "Sort must not be null or empty");
        }
    }
}
