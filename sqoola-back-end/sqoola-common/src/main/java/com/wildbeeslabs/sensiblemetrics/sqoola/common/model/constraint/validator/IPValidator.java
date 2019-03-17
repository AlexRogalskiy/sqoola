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

import com.wildbeeslabs.sensiblemetrics.sqoola.common.model.constraint.annotation.IP;
import org.apache.commons.lang3.StringUtils;
import org.h2.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link IP} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class IPValidator implements ConstraintValidator<IP, String> {

//    private static Pattern DEFAULT_IPV4_PATTERN = null;
//    private static Pattern DEFAULT_IPV6_PATTERN = null;
    /**
     * Default IPv4 pattern format
     */
    public static final String DEFAULT_IPV4_PATTERN_FORMAT = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    /**
     * Default IPv6 pattern format
     */
    public static final String DEFAULT_IPV6_PATTERN_FORMAT = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

    //    static {
//        try {
//            DEFAULT_IPV4_PATTERN = Pattern.compile(IPValidator.DEFAULT_IPV4_PATTERN_FORMAT, Pattern.CASE_INSENSITIVE);
//            DEFAULT_IPV6_PATTERN = Pattern.compile(IPValidator.DEFAULT_IPV6_PATTERN_FORMAT, Pattern.CASE_INSENSITIVE);
//        } catch (PatternSyntaxException e) {
//        }
//    }
    @Override
    public void initialize(final IP constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String ipField, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(ipField)) {
            return true;
        }
        boolean isValid = ipField.trim().toLowerCase().matches(IPValidator.DEFAULT_IPV4_PATTERN_FORMAT) || ipField.trim().toLowerCase().matches(IPValidator.DEFAULT_IPV6_PATTERN_FORMAT);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect IP address format ={%s})", ipField)).addConstraintViolation();
        }
        return isValid;
    }
}
