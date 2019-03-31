package com.sensiblemetrics.api.sqoola.common.annotation;

import java.lang.annotation.*;

/**
 * Api ignore constraint
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
public @interface ApiIgnore {

    /**
     * A brief description of why this parameter/operation is ignored
     *
     * @return the description of why it is ignored
     */
    String message() default "{ApiIgnore.message}";
}
