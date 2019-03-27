package com.sensiblemetrics.api.sqoola.common.validation.validator;

import de.pearl.pem.common.validation.model.GeneralSearchRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

/**
 * {@link GeneralSearchRequest} validator
 */
public class GeneralSearchRequestValidator extends GeneralRequestValidator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return GeneralSearchRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        super.validate(target, errors);
        final GeneralSearchRequest<?> request = (GeneralSearchRequest<?>) target;
        if (CollectionUtils.isEmpty(request.getItems())) {
            errors.rejectValue("items", "field.notNullOrEmpty", null, "Items must be null or empty");
        }
    }
}
