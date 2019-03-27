package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.Email2Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Email2Validator.class)
public @interface Email2 {

    String message() default "{Email2.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
