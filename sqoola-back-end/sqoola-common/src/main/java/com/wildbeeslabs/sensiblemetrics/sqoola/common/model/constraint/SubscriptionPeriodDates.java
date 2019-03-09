package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.validator.SubscriptionPeriodDatesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Subscription period dates constraint annotation
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {SubscriptionPeriodDatesValidator.class})
public @interface SubscriptionPeriodDates {

    String message() default "{com.dinamexoft.carol.triggers.models.constraints.SubscriptionPeriodDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
