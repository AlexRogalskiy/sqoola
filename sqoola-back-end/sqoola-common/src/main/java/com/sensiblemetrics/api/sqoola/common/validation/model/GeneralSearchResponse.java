package com.sensiblemetrics.api.sqoola.common.validation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Search {@link GeneralResponse} entity
 *
 * @param <T> type of search item
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeneralSearchResponse<T extends Serializable> extends GeneralResponse<SearchResult<T>> {

    /**
     * Returns binary flag based on errors in {@link List}
     *
     * @return true - if {@link List} contains errors, false - otherwise
     */
    public boolean hasErrors() {
        return CollectionUtils.isEmpty(getItems()) || getItems().stream().anyMatch(r -> !r.isSuccess());
    }
}
