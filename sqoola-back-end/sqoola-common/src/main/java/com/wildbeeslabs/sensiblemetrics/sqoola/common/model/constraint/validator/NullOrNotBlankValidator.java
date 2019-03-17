/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.validator;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.annotation.NullOrNotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link NullOrNotBlank} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {

    public static final String DEFAULT_BLANK_PATTERN_FORMAT = "^[^\\s]*$";

    private int max;
    private int min;

    @Override
    public void initialize(final NullOrNotBlank constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(final String nullOrNotBlankField, final ConstraintValidatorContext context) {
        if (Objects.isNull(nullOrNotBlankField)) {
            return true;
        }
        boolean isValid = nullOrNotBlankField.matches(NullOrNotBlankValidator.DEFAULT_BLANK_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect format={%s} in string={%s}", NullOrNotBlankValidator.DEFAULT_BLANK_PATTERN_FORMAT, nullOrNotBlankField)).addConstraintViolation();
            return false;
        }
        isValid = (nullOrNotBlankField.length() >= this.min && nullOrNotBlankField.length() <= this.max);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: length range: from {%s} to {%s} in string={%s}", this.min, this.max, nullOrNotBlankField)).addConstraintViolation();
            return false;
        }
        return isValid;
    }
}
