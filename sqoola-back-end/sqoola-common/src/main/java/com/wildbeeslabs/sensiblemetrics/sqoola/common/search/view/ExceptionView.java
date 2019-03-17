/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wildbeeslabs.sensiblemetrics.sqoola.common.search.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.view.interfaces.ExposableExceptionView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import static com.wildbeeslabs.sensiblemetrics.supersolr.search.view.interfaces.ExposableExceptionView.*;
import static com.wildbeeslabs.sensiblemetrics.supersolr.utility.DateUtils.DEFAULT_DATE_FORMAT_LOCALE;
import static com.wildbeeslabs.sensiblemetrics.supersolr.utility.DateUtils.DEFAULT_DATE_FORMAT_PATTERN_EXT;

/**
 * Exception document view
 */
@Builder
@Data
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {
    PATH_FIELD_NAME,
    CODE_FIELD_NAME,
    TYPE_FIELD_NAME,
    DESCRIPTION_FIELD_NAME,
    MESSAGE_FIELD_NAME,
    TIMESTAMP_FIELD_NAME
}, alphabetic = true)
@JacksonXmlRootElement(localName = ExposableExceptionView.VIEW_ID)
@ApiModel(value = ExposableExceptionView.VIEW_ID, description = "All details about exception document")
public class ExceptionView implements ExposableExceptionView, Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -328743858984114195L;

    @ApiModelProperty(value = "Exception url", name = "path", example = "/pem-gateway-template/149", required = true)
    @JacksonXmlProperty(localName = PATH_FIELD_NAME)
    @JsonProperty(PATH_FIELD_NAME)
    private String path;

    @ApiModelProperty(value = "Exception code", name = "code", example = "500", required = true)
    @JacksonXmlProperty(localName = CODE_FIELD_NAME)
    @JsonProperty(CODE_FIELD_NAME)
    private Integer code;

    @ApiModelProperty(value = "Exception type", name = "type", example = "Internal Server Error", required = true)
    @JacksonXmlProperty(localName = TYPE_FIELD_NAME)
    @JsonProperty(TYPE_FIELD_NAME)
    private String type;

    @ApiModelProperty(value = "Exception description", name = "description", example = "org.springframework.dao.EmptyResultDataAccessException")
    @JacksonXmlProperty(localName = DESCRIPTION_FIELD_NAME)
    @JsonProperty(DESCRIPTION_FIELD_NAME)
    private String description;

    @ApiModelProperty(value = "Exception message", name = "message", example = "Incorrect result size: expected 1, actual 0")
    @JacksonXmlProperty(localName = MESSAGE_FIELD_NAME)
    @JsonProperty(MESSAGE_FIELD_NAME)
    private String message;

    @ApiModelProperty(value = "Exception timestamp", name = "timestamp", example = "1551372385400", required = true)
    @JacksonXmlProperty(localName = TIMESTAMP_FIELD_NAME)
    @JsonProperty(TIMESTAMP_FIELD_NAME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DEFAULT_DATE_FORMAT_PATTERN_EXT, locale = DEFAULT_DATE_FORMAT_LOCALE)
    private Date timestamp;
}
