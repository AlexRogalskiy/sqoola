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
import com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableCategoryView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.ID_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.SCORE_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableCategoryView.*;

/**
 * CategoryEntity document dto {@link BaseModelView}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {
    ID_FIELD_NAME,
    SCORE_FIELD_NAME,
    INDEX_FIELD_NAME,
    TITLE_FIELD_NAME,
    DESCRIPTION_FIELD_NAME,
    PRODUCTS_FIELD_NAME,
    MAIN_PRODUCTS_FIELD_NAME
}, alphabetic = true)
@JacksonXmlRootElement(localName = ExposableCategoryView.VIEW_ID, namespace = "io.sqoola")
@ApiModel(value = ExposableCategoryView.VIEW_ID, description = "All details about category document")
public class BaseCategoryView extends BaseModelView<Long> implements ExposableCategoryView {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -878245565646636436L;

    @ApiModelProperty(value = "CategoryEntity index", name = "index", example = "index", required = true)
    @JacksonXmlProperty(localName = INDEX_FIELD_NAME)
    @JsonProperty(INDEX_FIELD_NAME)
    private Integer index;

    @ApiModelProperty(value = "CategoryEntity title", name = "title", example = "title", required = true)
    @JacksonXmlProperty(localName = TITLE_FIELD_NAME)
    @JsonProperty(TITLE_FIELD_NAME)
    private String title;

    @ApiModelProperty(value = "CategoryEntity description", name = "description", example = "description")
    @JacksonXmlProperty(localName = DESCRIPTION_FIELD_NAME)
    @JsonProperty(DESCRIPTION_FIELD_NAME)
    private String description;

    @ApiModelProperty(value = "List of products per category", name = "products", example = "products")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = PRODUCTS_FIELD_NAME)
    @JsonProperty(PRODUCTS_FIELD_NAME)
    private final Set<ProductView> products = new HashSet<>();

    @ApiModelProperty(value = "List of main products per category", name = "mainProducts", example = "mainProducts")
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = MAIN_PRODUCTS_FIELD_NAME)
    @JsonProperty(MAIN_PRODUCTS_FIELD_NAME)
    private final Set<ProductView> mainProducts = new HashSet<>();
}
