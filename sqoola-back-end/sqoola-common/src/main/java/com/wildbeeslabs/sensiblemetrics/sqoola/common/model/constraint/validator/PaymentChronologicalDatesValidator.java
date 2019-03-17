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

import com.wildbeeslabs.api.rest.common.model.BasePaymentEntity;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.annotation.PaymentChronologicalDates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Objects;

/**
 * {@link PaymentChronologicalDates} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class PaymentChronologicalDatesValidator implements ConstraintValidator<PaymentChronologicalDates, BasePaymentEntity> {

    @Override
    public void initialize(final PaymentChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(final BasePaymentEntity payment, final ConstraintValidatorContext context) {
        if (Objects.isNull(payment.getPayedAt())) {
            return true;
        }
        final Date defaultDate = Objects.isNull(payment.getModifiedAt()) ? payment.getCreatedAt() : payment.getModifiedAt();
        boolean isValid = defaultDate.getTime() < payment.getPayedAt().getTime();
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect payment chronological dates: createdAt/modifiedAt={%s} < payedAt={%s}", payment.getCreatedAt(), payment.getPayedAt())).addConstraintViolation();
            return false;
        }
        return isValid;
    }
}
