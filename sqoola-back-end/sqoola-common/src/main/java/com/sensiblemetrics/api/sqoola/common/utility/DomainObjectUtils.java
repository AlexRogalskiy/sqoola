package com.sensiblemetrics.api.sqoola.common.utility;

import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * Utility code for domain classes.
 */
@UtilityClass
public class DomainObjectUtils {

    /**
     * @param actual actual value
     * @param safe   a null-safe value
     * @param <T>    type
     * @return actual value, if it's not null, or safe value if the actual value is null.
     */
    public static <T> T nullSafe(final T actual, final T safe) {
        return Objects.isNull(actual) ? safe : actual;
    }
}
