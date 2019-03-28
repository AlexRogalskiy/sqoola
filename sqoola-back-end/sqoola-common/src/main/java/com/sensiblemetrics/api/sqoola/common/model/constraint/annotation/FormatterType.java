package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

/**
 * Formatter type constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Qualifier
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface FormatterType {

    String value();
}
