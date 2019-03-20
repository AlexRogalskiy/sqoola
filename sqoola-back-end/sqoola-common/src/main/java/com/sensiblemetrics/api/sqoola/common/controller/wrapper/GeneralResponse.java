package com.sensiblemetrics.api.sqoola.common.controller.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * General search response entity
 *
 * @param <T> type of search result
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "response")
public class GeneralResponse<T extends Serializable> {

    @JsonProperty("item")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "items")
    private Collection<T> items;

//    /**
//     * Returns binary flag based on errors in items {@link List}
//     *
//     * @return true - if items {@link List} contains errors, false - otherwise
//     */
//    public boolean hasErrors() {
//        return CollectionUtils.isEmpty(getItems()) || getItems().stream().anyMatch(r -> !r.isSuccess());
//    }
}
