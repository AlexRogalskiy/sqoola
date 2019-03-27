package com.sensiblemetrics.api.sqoola.common.validation.validator;

import de.pearl.pem.common.validation.model.GeneralParamRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;

/**
 * {@link GeneralParamRequest} validator
 */
public class GeneralParamRequestValidator extends GeneralRequestValidator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return GeneralParamRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        super.validate(target, errors);
        final GeneralParamRequest<?> request = (GeneralParamRequest<?>) target;
        if (CollectionUtils.isEmpty(request.getItems())) {
            errors.rejectValue("items", "field.notNullOrEmpty", null, "Items must be null or empty");
        }
    }
}
