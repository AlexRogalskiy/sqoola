package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * Web site constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Field <webSite> in only allowed in the following format={regexp} with flags={flags}")
public @interface WebSite {

    @Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        WebSite[] value();
    }

    String message() default "{WebSite.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
