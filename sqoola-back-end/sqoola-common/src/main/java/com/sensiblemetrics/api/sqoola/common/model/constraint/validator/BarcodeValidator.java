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
package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.Barcode;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link Barcode} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class BarcodeValidator implements ConstraintValidator<Barcode, String> {

    /**
     * Default bar code pattern format
     */
    public static final String DEFAULT_ORDER_BARCODE_PATTERN_FORMAT = ".[A-Z][0-9].*?";

    @Override
    public void initialize(final Barcode constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String orderField, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(orderField)) {
            return false;
        }
        boolean isValid = orderField.trim().matches(BarcodeValidator.DEFAULT_ORDER_BARCODE_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect bar code ={%s} (expected format={%s})", orderField, BarcodeValidator.DEFAULT_ORDER_BARCODE_PATTERN_FORMAT)).addConstraintViolation();
        }
        return isValid;
    }
}
