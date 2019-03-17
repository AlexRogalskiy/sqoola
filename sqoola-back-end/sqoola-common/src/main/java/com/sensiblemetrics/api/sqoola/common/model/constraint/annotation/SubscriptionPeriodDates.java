package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.SubscriptionPeriodDatesConstraintValidator;

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
@Constraint(validatedBy = {SubscriptionPeriodDatesConstraintValidator.class})
public @interface SubscriptionPeriodDates {

    String message() default "{SubscriptionPeriodDates.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
