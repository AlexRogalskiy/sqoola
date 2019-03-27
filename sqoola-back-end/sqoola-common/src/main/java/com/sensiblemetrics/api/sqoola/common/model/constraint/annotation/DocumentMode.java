package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.DocumentModeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Document mode constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {DocumentModeValidator.class})
public @interface DocumentMode {

    String value() default "";

    String message() default "{DocumentMode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
