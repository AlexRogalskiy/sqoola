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
package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.OrderChronologicalDates;
import com.wildbeeslabs.api.rest.common.model.BaseOrderDataEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Objects;

/**
 * {@link OrderChronologicalDates} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class OrderChronologicalDatesValidator implements ConstraintValidator<OrderChronologicalDates, BaseOrderDataEntity> {

    @Override
    public void initialize(final OrderChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(final BaseOrderDataEntity order, final ConstraintValidatorContext context) {
        if (Objects.isNull(order.getPayment()) && Objects.isNull(order.getDelivery())) {
            return true;
        }
        boolean isValid = true;
        final Date defaultDate = Objects.isNull(order.getModifiedAt()) ? order.getCreatedAt() : order.getModifiedAt();
        if (Objects.nonNull(order.getPayment()) && Objects.nonNull(order.getPayment().getPayedAt()) && Objects.nonNull(order.getDelivery()) && Objects.nonNull(order.getDelivery().getDeliveredAt())) {
            isValid = (defaultDate.getTime() < order.getPayment().getPayedAt().getTime() && order.getPayment().getPayedAt().getTime() < order.getDelivery().getDeliveredAt().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect order chronological dates: createdAt/modifiedAt={%s} < payedAt={%s} < deliveredAt={%s}", order.getCreatedAt(), order.getPayment().getPayedAt(), order.getDelivery().getDeliveredAt())).addConstraintViolation();
                return false;
            }
            return true;
        }
        if (Objects.nonNull(order.getPayment()) && Objects.nonNull(order.getPayment().getPayedAt())) {
            isValid = (defaultDate.getTime() < order.getPayment().getPayedAt().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect order chronological dates: createdAt/modifiedAt={%s} < payedAt={%s}", order.getCreatedAt(), order.getPayment().getPayedAt())).addConstraintViolation();
                return false;
            }
        }
        if (Objects.nonNull(order.getDelivery()) && Objects.nonNull(order.getDelivery().getDeliveredAt())) {
            isValid = (defaultDate.getTime() < order.getDelivery().getDeliveredAt().getTime());
            if (!isValid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect order chronological dates: createdAt/modifiedAt={%s} <  deliveredAt={%s}", order.getCreatedAt(), order.getDelivery().getDeliveredAt())).addConstraintViolation();
                return false;
            }
        }
        return isValid;
    }
}
