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
 * Search param entity
 *
 * @param <T> type of search param
 */
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "param")
public class SearchParam<T extends Serializable> implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -2069850428140731290L;

    /**
     * Default param key
     */
    @Getter
    @JsonProperty("key")
    @JacksonXmlProperty(localName = "key")
    private String key;

    /**
     * Default param value
     */
    @Getter
    @JsonProperty("value")
    @JacksonXmlProperty(localName = "value")
    private T value;
}
