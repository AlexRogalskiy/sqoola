package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Data access constraint annotation
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface DataAccess {
    Class<?> entity();
}
