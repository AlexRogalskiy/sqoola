package com.sensiblemetrics.api.sqoola.common.utility;

import com.sensiblemetrics.api.sqoola.common.validation.model.GeneralSearchRequest;
import com.sensiblemetrics.api.sqoola.common.validation.model.SearchKey;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Request utilities implementation
 */
@Slf4j
@UtilityClass
public class RequestUtils {

    /**
     * Returns {@link List} collection of items {@code T} by input {@link GeneralSearchRequest} request
     *
     * @param <T>     type of {@link List} item
     * @param request - initial input {@link GeneralSearchRequest} request
     * @return {@link List} collection of items {@code T}
     */
    public <T extends Serializable> List<T> getItems(final GeneralSearchRequest<T> request) {
        return Optional.ofNullable(request.getItems()).orElseGet(Collections::emptyList).stream().map(SearchKey::getItem).collect(Collectors.toList());
    }
}
