package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.validator;

import com.dinamexoft.carol.triggers.models.subscription.period.SubscriptionOperationPeriod;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.annotation.SubscriptionPeriodDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link SubscriptionPeriodDates} constraint validator implementation {@link ConstraintValidator}
 *
 * @version 1.0.0
 * @since 2017-08-08
 */
public class SubscriptionPeriodDatesConstraintValidator implements ConstraintValidator<SubscriptionPeriodDates, SubscriptionOperationPeriod> {

    @Override
    public void initialize(final SubscriptionPeriodDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(final SubscriptionOperationPeriod subscriptionOperationPeriod, final ConstraintValidatorContext context) {
        if (Objects.isNull(subscriptionOperationPeriod.getStarted()) || Objects.isNull(subscriptionOperationPeriod.getExpired())) {
            return true;
        }
        boolean isValid = subscriptionOperationPeriod.getStarted().getTime() < subscriptionOperationPeriod.getExpired().getTime();
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect subscription chronological dates: started={%s}, expired={%s} (expected dates: started < expired)", subscriptionOperationPeriod.getStarted(), subscriptionOperationPeriod.getExpired())).addConstraintViolation();
        }
        return isValid;
    }
}

