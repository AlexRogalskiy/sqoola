/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.utility;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Property utilities implementation
 */
@Slf4j
@UtilityClass
public class PropertyUtils {

    /**
     * Returns property map {@link Properties} by input file name
     *
     * @param fileName - initial file name {@link String}
     * @return property map {@link Properties}
     */
    public static Properties loadProperties(final String fileName) {
        final Properties properties = new Properties();
        try (final InputStream input = PropertyUtils.class.getResourceAsStream(fileName)) {
            if (Objects.isNull(input)) {
                throw new IllegalStateException(String.format("ERROR: property resource file={%s} is not found", fileName));
            }
            properties.load(input);
        } catch (IOException e) {
            log.error(String.format("ERROR: cannot process resource file={%s}, message={%s}", fileName, e.getMessage()));
        }
        return properties;
    }

    /**
     * Returns string property value {@link String} by input key {@link String}
     *
     * @param properties  - initial collection of properties {@link Properties}
     * @param propertyKey - initial property key {@link String}
     * @return property value {@link String}
     */
    public static String getStringProperty(final Properties properties, final String propertyKey) {
        Objects.requireNonNull(properties);
        Objects.requireNonNull(propertyKey);

        if (!properties.containsKey(propertyKey)) {
            throw new IllegalArgumentException(String.format("ERROR: cannot read property {%s} from resource {%s}", propertyKey, properties));
        }
        return properties.getProperty(propertyKey);
    }

    /**
     * Returns boolean property value {@link Boolean} by input key {@link String}
     *
     * @param properties  - initial collection of properties {@link Properties}
     * @param propertyKey - initial property key {@link String}
     * @return property value {@link Boolean}
     */
    public static boolean getBooleanProperty(final Properties properties, final String propertyKey) {
        return Boolean.parseBoolean(getStringProperty(properties, propertyKey));
    }

    /**
     * Returns enum property value {@link Enum} by input key {@link String} and enum type {@link Class}
     *
     * @param properties  - initial collection of properties {@link Properties}
     * @param propertyKey - initial property key {@link String}
     * @param enumType    - initial property type {@link Enum}
     * @param <T>
     * @return property value {@link Enum}
     */
    public static <T extends Enum<T>> T getEnumProperty(final Properties properties, final String propertyKey, final Class<T> enumType) {
        final String enumName = getStringProperty(properties, propertyKey);
        try {
            return Enum.valueOf(enumType, enumName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("ERROR: cannot read enum property {%s} by key {%s} from resource {%s}", enumName, propertyKey, properties));
        }
    }
}
