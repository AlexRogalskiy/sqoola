package com.sensiblemetrics.api.sqoola.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Converter
public @interface ConverterComponent {

    /**
     * The value may indicate a suggestion for a logical component name, to be turned into
     * a Spring bean in case of an autodetected component.
     *
     * @return the component name
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

}
