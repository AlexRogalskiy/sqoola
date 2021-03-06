/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableAttributeView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableAttributeView.*;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.ID_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.SCORE_FIELD_NAME;


/**
 * AttributeEntity document view {@link BaseModelView} implementation
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {
    ID_FIELD_NAME,
    SCORE_FIELD_NAME,
    NAME_FIELD_NAME,
    SYNONYM_FIELD_NAME,
    DESCRIPTION_TEXT_FIELD_NAME,
    KEYWORDS_FIELD_NAME,
    PRODUCTS_FIELD_NAME
}, alphabetic = true)
@JacksonXmlRootElement(localName = ExposableAttributeView.VIEW_ID, namespace = "io.sqoola")
@ApiModel(value = ExposableAttributeView.VIEW_ID, description = "All details about attribute document")
public class AttributeView extends BaseModelView<String> implements ExposableAttributeView {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -6304630916069644472L;

    @ApiModelProperty(value = "AttributeEntity name", name = "name", example = "name", required = true)
    @JacksonXmlProperty(localName = NAME_FIELD_NAME)
    @JsonProperty(NAME_FIELD_NAME)
    private String name;

    @ApiModelProperty(value = "AttributeEntity name synonym", name = "synonym", example = "short remark")
    @JacksonXmlProperty(localName = SYNONYM_FIELD_NAME)
    @JsonProperty(SYNONYM_FIELD_NAME)
    private String name2;

    @ApiModelProperty(value = "AttributeEntity description", name = "description", example = "description")
    @JacksonXmlProperty(localName = DESCRIPTION_TEXT_FIELD_NAME)
    @JsonProperty(DESCRIPTION_TEXT_FIELD_NAME)
    private String descriptionText;

    @ApiModelProperty(value = "AttributeEntity keywords", name = "keywords", example = "keywords")
    @JacksonXmlProperty(localName = KEYWORDS_FIELD_NAME)
    @JsonProperty(KEYWORDS_FIELD_NAME)
    private String keywords;

    @ApiModelProperty(value = "List of products per attribute", name = "products", allowableValues = "available,pending,sold")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = PRODUCTS_FIELD_NAME)
    @JsonProperty(PRODUCTS_FIELD_NAME)
    private final List<ProductView> products = new ArrayList<>();
}
