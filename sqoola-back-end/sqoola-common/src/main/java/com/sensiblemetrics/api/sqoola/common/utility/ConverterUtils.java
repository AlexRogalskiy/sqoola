package com.sensiblemetrics.api.sqoola.common.utility;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.MapPropertySource;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Converter utilities implementation
 */
@Slf4j
@UtilityClass
public class ConverterUtils {

    /**
     * Default custom prefix
     */
    public final static String CUSTOM_PREFIX = "custom.";

    public static <T> Iterable<T> toIterable(@NonNull final Optional<T> o) {
        return o.map(Collections::singleton).orElseGet(Collections::emptySet);
    }

    public static List<MapPropertySource> convertEntrySet(Set<Map.Entry> entrySet, Optional<String> parentKey) {
        return entrySet.stream()
            .map((Map.Entry e) -> convertToPropertySourceList(e, parentKey))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    public static List<MapPropertySource> convertToPropertySourceList(Map.Entry e, Optional<String> parentKey) {
        String key = parentKey.map(s -> s + ".")
            .orElse("") + (String) e.getKey();
        Object value = e.getValue();
        return covertToPropertySourceList(key, value);
    }

    @SuppressWarnings("unchecked")
    public static List<MapPropertySource> covertToPropertySourceList(String key, Object value) {
        if (value instanceof LinkedHashMap) {
            LinkedHashMap map = (LinkedHashMap) value;
            Set<Map.Entry> entrySet = map.entrySet();
            return convertEntrySet(entrySet, Optional.ofNullable(key));
        }
        String finalKey = CUSTOM_PREFIX + key;
        return Collections.singletonList(new MapPropertySource(finalKey, Collections.singletonMap(finalKey, value)));
    }
}
