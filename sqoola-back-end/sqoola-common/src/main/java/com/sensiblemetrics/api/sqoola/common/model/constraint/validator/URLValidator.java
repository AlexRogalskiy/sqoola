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

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.URL;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.util.Objects;

/**
 * {@link URL} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class URLValidator implements ConstraintValidator<URL, String> {

    /**
     * Empty url port
     */
    public static final int DEFAULT_PORT_VALUE = -1;
    /**
     * Default url protocol
     */
    private String protocol;
    /**
     * Default url host
     */
    private String host;
    /**
     * Default url port
     */
    private int port;

    @Override
    public void initialize(final URL constraintAnnotation) {
        this.protocol = constraintAnnotation.protocol();
        this.host = constraintAnnotation.host();
        this.port = constraintAnnotation.port();
    }

    @Override
    public boolean isValid(final String urlField, final ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(urlField)) {
            return true;
        }
        @SuppressWarnings("UnusedAssignment")
        java.net.URL url = null;
        try {
            url = new java.net.URL(urlField);
        } catch (MalformedURLException ex) {
            return false;
        }
        boolean isValid = StringUtils.isEmpty(this.protocol) || Objects.equals(url.getProtocol(), this.protocol);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect protocol format={%s} in url={%s}", this.protocol, urlField)).addConstraintViolation();
            return false;
        }
        isValid = StringUtils.isEmpty(this.host) || StringUtils.startsWithIgnoreCase(url.getHost(), this.host);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect host format={%s} in url={%s}", this.host, urlField)).addConstraintViolation();
            return false;
        }
        isValid = Objects.equals(this.port, URLValidator.DEFAULT_PORT_VALUE) || !Objects.equals(url.getPort(), this.port);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("ERROR: incorrect port format={%s} in url={%s}", this.port, urlField)).addConstraintViolation();
            return false;
        }
        return true;
    }
}
