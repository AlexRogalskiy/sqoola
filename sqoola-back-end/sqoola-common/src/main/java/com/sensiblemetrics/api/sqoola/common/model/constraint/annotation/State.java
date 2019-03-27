package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.StateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * State constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = StateValidator.class)
public @interface State {

    String message() default "{State.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "";
}
