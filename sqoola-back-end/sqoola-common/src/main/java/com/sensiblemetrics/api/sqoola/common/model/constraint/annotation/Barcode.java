/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.api.sqoola.common.model.constraint.annotation;

import com.sensiblemetrics.api.sqoola.common.model.constraint.validator.BarcodeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Barcode constraint annotation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = {BarcodeValidator.class})
@Pattern(regexp = BarcodeValidator.DEFAULT_ORDER_BARCODE_PATTERN_FORMAT, flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.UNICODE_CASE}, message = "Field <barcode> is only allowed in the following format={regexp} with flags={flags}")
@Size(max = 30, message = "Field <barcode> is only allowed in the following length range: from {min} to {max}")
@NotBlank(message = "Field <barcode> cannot be blank")
public @interface Barcode {

    @Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(value = RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        Barcode[] value();
    }

    String message() default "{Barcode.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
