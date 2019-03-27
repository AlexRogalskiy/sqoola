package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
