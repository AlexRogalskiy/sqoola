/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
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

import com.wildbeeslabs.api.rest.common.model.BasePolicyEntity;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.annotation.OrderLineChronologicalDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Objects;

/**
 * {@link OrderLineChronologicalDates} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class PolicyChronologicalDatesValidator implements ConstraintValidator<OrderLineChronologicalDates, BasePolicyEntity> {

    @Override
    public void initialize(final OrderLineChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(final BasePolicyEntity policy, final ConstraintValidatorContext context) {
        if (Objects.isNull(policy.getPeriodData())) {
            return true;
        }
        boolean isValid = true;
        final Date defaultDate = Objects.isNull(policy.getModifiedAt()) ? policy.getCreatedAt() : policy.getModifiedAt();
        if (Objects.nonNull(policy.getPeriodData().getFromDate()) && Objects.nonNull(policy.getPeriodData().getToDate())) {
            isValid = (defaultDate.getTime() < policy.getPeriodData().getFromDate().getTime() && policy.getPeriodData().getFromDate().getTime() < policy.getPeriodData().getToDate().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect chronological dates order: createdAt/modifiedAt={%s} < startedAt={%s} < expiredAt={%s}", policy.getCreatedAt(), policy.getPeriodData().getFromDate(), policy.getPeriodData().getToDate())).addConstraintViolation();
                return false;
            }
            return true;
        }
        if (Objects.nonNull(policy.getPeriodData().getToDate())) {
            isValid = (defaultDate.getTime() < policy.getPeriodData().getToDate().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect chronological dates order: createdAt/modifiedAt={%s} < expiredAt={%s}", policy.getCreatedAt(), policy.getPeriodData().getToDate())).addConstraintViolation();
                return false;
            }
        }
        if (Objects.nonNull(policy.getPeriodData().getFromDate())) {
            isValid = (defaultDate.getTime() < policy.getPeriodData().getFromDate().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect chronological dates order: createdAt/modifiedAt={%s} < startedAt={%s}", policy.getCreatedAt(), policy.getPeriodData().getFromDate())).addConstraintViolation();
                return false;
            }
        }
        return isValid;
    }
}
