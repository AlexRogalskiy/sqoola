package com.sensiblemetrics.api.sqoola.common.serializer;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public interface Serializer<T> {

    Pattern COMMA_SEP_PATTERN = Pattern.compile(",");
    String COMMA_SEP = ",";

    T read(String repr);
    String write(T obj);


    default List<T> readList(String repr) {
        if (repr == null) {
            return Collections.emptyList();
        }

        return COMMA_SEP_PATTERN
            .splitAsStream(repr)
            .map(this::read)
            .collect(Collectors.toList());
    }

    default String writeList(List<T> elems) {
        if (elems == null || elems.isEmpty()) {
            return null;
        }

        return elems.stream()
            .map(this::write)
            .collect(Collectors.joining(COMMA_SEP));
    }
}
