package com.sensiblemetrics.api.sqoola.common.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Search result entity
 *
 * @param <T> type of search item
 */
@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "result")
public class SearchResult<T extends Serializable> implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 8620973562870664729L;

    /**
     * Default search item {@code T}
     */
    @NonNull
    @JsonProperty("item")
    @JacksonXmlProperty(localName = "item")
    private T item;

    /**
     * Default error message list {@link List}
     */
    @JsonProperty("error")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "errors")
    private final Collection<String> errors = new ArrayList<>();

    /**
     * Default {@link SearchResult} constructor by input parameters
     *
     * @param key          - initial input search item key {@code T}
     * @param errorMessage - initial input error message
     */
    public SearchResult(final T key, final String errorMessage) {
        this(key);
        this.addError(errorMessage);
    }

    public boolean isSuccess() {
        return CollectionUtils.isEmpty(this.getErrors());
    }

    public void addError(final String errorMessage) {
        if (Objects.nonNull(errorMessage)) {
            this.getErrors().add(errorMessage);
        }
    }
}
