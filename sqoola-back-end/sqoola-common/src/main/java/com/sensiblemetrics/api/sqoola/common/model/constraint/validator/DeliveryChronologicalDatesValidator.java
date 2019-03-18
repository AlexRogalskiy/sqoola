/*
 * The MIT License
 *
 * Copyright 2018 WildBees Labs.
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

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.DeliveryChronologicalDates;
import com.wildbeeslabs.api.rest.common.model.BaseDeliveryEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Objects;

/**
 * {@link DeliveryChronologicalDates} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class DeliveryChronologicalDatesValidator implements ConstraintValidator<DeliveryChronologicalDates, BaseDeliveryEntity> {

    @Override
    public void initialize(final DeliveryChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(final BaseDeliveryEntity delivery, final ConstraintValidatorContext context) {
        if (Objects.isNull(delivery.getDeliveredAt())) {
            return true;
        }
        final Date defaultDate = Objects.isNull(delivery.getModifiedAt()) ? delivery.getCreatedAt() : delivery.getModifiedAt();
        boolean isValid = defaultDate.getTime() < delivery.getDeliveredAt().getTime();
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect delivery chronological dates: createdAt/modifiedAt={%s} < delivered={%s}", delivery.getCreatedAt(), delivery.getDeliveredAt())).addConstraintViolation();
            return false;
        }
        return isValid;
    }
}
