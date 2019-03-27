package com.sensiblemetrics.api.sqoola.common.validation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Search key entity
 *
 * @param <T> type of search key
 */
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "key")
public class SearchKey<T extends Serializable> implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -2761481783742372926L;

    @Getter
    @JsonProperty("item")
    @JacksonXmlProperty(localName = "item")
    private T item;

    @Getter
    @JsonProperty("code")
    @JacksonXmlProperty(localName = "code")
    private String code;

    @Getter
    @JsonProperty("description")
    @JacksonXmlProperty(localName = "description")
    private String description;
}
