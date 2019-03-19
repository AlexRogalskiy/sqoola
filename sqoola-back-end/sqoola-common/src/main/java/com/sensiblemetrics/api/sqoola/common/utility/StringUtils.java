/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.sensiblemetrics.api.sqoola.common.utility;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * Custom string utilities implementation
 */
@Slf4j
@UtilityClass
public class StringUtils {

    /**
     * Default numeric pattern format
     */
    public static final String DEFAULT_FORMAT_PATTERN = "#.##";
    /**
     * Default decimal format instance {@link DecimalFormat}
     */
    public static final ThreadLocal<DecimalFormat> DEFAULT_DECIMAL_FORMATTER = ThreadLocal.withInitial(() -> {
        final DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
        decimalSymbols.setDecimalSeparator('.');
        return new DecimalFormat(DEFAULT_FORMAT_PATTERN, decimalSymbols);
    });

    /**
     * Returns localized string message {@link String} by initial message source {@link MessageSource} and raw string message {@link String}
     *
     * @param messageSource - initial input message source {@link MessageSource}
     * @param message       - initial input raw string message {@link String}
     * @return localized string message {@link String}
     */
    public static String getLocaleMessage(@NonNull final MessageSource messageSource, final String message) {
        final Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(Optional.ofNullable(message).orElse(Strings.EMPTY), null, locale);
    }

    /**
     * Returns formatted string message {@link String} by initial message source {@link MessageSource}, raw string message {@link String} and collection of arguments
     *
     * @param messageSource - initial input message source {@link MessageSource}
     * @param message       - initial input raw string message {@link String}
     * @param args          - initial input collection of arguments
     * @return formatted string message {@link String}
     */
    public static String formatMessage(final MessageSource messageSource, final String message, final Object... args) {
        return String.format(getLocaleMessage(messageSource, message), args);
    }

    /**
     * Returns string formatted decimal number by default formatter instance {@link DecimalFormat}
     *
     * @param num - initial input decimal number
     * @return string formatted decimal number
     */
    public static String format(double num) {
        return DEFAULT_DECIMAL_FORMATTER.get().format(num);
    }

    public static String getString(final String... values) {
        return getStringByDelimiter(org.apache.commons.lang3.StringUtils.EMPTY, values);
    }

    public static String getStringByDelimiter(final String delimiter, final String... values) {
        return org.apache.commons.lang3.StringUtils.join(values, delimiter);
    }
}
