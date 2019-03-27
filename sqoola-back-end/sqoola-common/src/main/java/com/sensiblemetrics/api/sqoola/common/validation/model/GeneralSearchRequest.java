package com.sensiblemetrics.api.sqoola.common.validation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Search {@link GeneralRequest} entity
 *
 * @param <T> type of search item
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeneralSearchRequest<T extends Serializable> extends GeneralRequest<SearchKey<T>> {

    @JsonProperty("keyword")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "keywords")
    private Collection<String> keywords;
}
