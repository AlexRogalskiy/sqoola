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

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.Address;
import com.wildbeeslabs.api.rest.common.model.BaseAddressEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link Address} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class AddressValidator implements ConstraintValidator<Address, BaseAddressEntity> {

    /**
     * Default street location pattern format
     */
    public static final String DEFAULT_STREET_ADDRESS_PATTERN_FORMAT = "^[\\p{Alnum}]$";
    /**
     * Default house location pattern format
     */
    public static final String DEFAULT_HOUSE_ADDRESS_PATTERN_FORMAT = "^[\\p{Alnum}]$";
    /**
     * Default zip code pattern format
     */
    public static final String DEFAULT_ZIP_CODE_PATTERN_FORMAT = "^[\\p{Alnum}]$";

    @Override
    public void initialize(final Address constraintAnnotation) {
    }

    @Override
    public boolean isValid(final BaseAddressEntity baseAddressEntity, final ConstraintValidatorContext context) {
        if (Objects.isNull(baseAddressEntity)) {
            return true;
        }
        boolean isValid = Objects.isNull(baseAddressEntity.getStreetAddress()) || baseAddressEntity.getStreetAddress().matches(AddressValidator.DEFAULT_STREET_ADDRESS_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect street location format ={%s} (expected format={%s})", baseAddressEntity.getStreetAddress(), AddressValidator.DEFAULT_STREET_ADDRESS_PATTERN_FORMAT)).addConstraintViolation();
            return false;
        }
        isValid = Objects.isNull(baseAddressEntity.getHouseAddress()) || baseAddressEntity.getHouseAddress().matches(AddressValidator.DEFAULT_HOUSE_ADDRESS_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect house location format ={%s} (expected format={%s})", baseAddressEntity.getHouseAddress(), AddressValidator.DEFAULT_HOUSE_ADDRESS_PATTERN_FORMAT)).addConstraintViolation();
            return false;
        }
        isValid = Objects.isNull(baseAddressEntity.getZipCode()) || baseAddressEntity.getZipCode().matches(AddressValidator.DEFAULT_ZIP_CODE_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect zip code format ={%s} (expected format={%s})", baseAddressEntity.getZipCode(), AddressValidator.DEFAULT_ZIP_CODE_PATTERN_FORMAT)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
