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
 * General search request entity
 *
 * @param <T> type of search key
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "request")
public class GeneralRequest<T extends Serializable> {

    @JsonProperty("item")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "items")
    private Collection<T> items;

    @JacksonXmlProperty(localName = "keywords")
    @JsonProperty("keywords")
    private Collection<String> keywords;

    @JacksonXmlProperty(localName = "page")
    @JsonProperty("page")
    int page;
}
