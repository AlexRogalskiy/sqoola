package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.PersonAgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Person age constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = PersonAgeValidator.class)
public @interface PersonAge {

    String message() default "{PersonAge.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
