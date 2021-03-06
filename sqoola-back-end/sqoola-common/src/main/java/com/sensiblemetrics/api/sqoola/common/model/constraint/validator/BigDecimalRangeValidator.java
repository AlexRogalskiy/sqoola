/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
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

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.BigDecimalRange;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * {@link BigDecimalRange} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class BigDecimalRangeValidator implements ConstraintValidator<BigDecimalRange, BigDecimal> {

    /**
     * Default max range precision
     */
    private long maxPrecision;
    /**
     * Default min range precision
     */
    private long minPrecision;
    /**
     * Default range scale
     */
    private int scale;

    @Override
    public void initialize(final BigDecimalRange constraintAnnotation) {
        this.maxPrecision = constraintAnnotation.maxPrecision();
        this.minPrecision = constraintAnnotation.minPrecision();
        this.scale = constraintAnnotation.scale();
    }

    @Override
    public boolean isValid(final BigDecimal bigDecimalRangeField, final ConstraintValidatorContext context) {
        if (Objects.isNull(bigDecimalRangeField)) {
            return true;
        }
        boolean isValid = (bigDecimalRangeField.precision() >= this.minPrecision && bigDecimalRangeField.precision() <= this.maxPrecision);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect precision format= from {%s} to {%s} in number={%s}", this.minPrecision, this.maxPrecision, bigDecimalRangeField)).addConstraintViolation();
            return false;
        }
        isValid = (bigDecimalRangeField.scale() <= this.scale);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect scale format= to {%s} in number={%s}", this.scale, bigDecimalRangeField)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
