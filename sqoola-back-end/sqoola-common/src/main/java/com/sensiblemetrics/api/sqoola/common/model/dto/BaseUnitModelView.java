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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableUnitModelView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.ID_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.SCORE_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableCategoryView.*;

/**
 * Base unit model view {@link BaseModelView}
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
@JacksonXmlRootElement(localName = ExposableUnitModelView.VIEW_ID, namespace = "io.sqoola")
@ApiModel(value = ExposableUnitModelView.VIEW_ID, description = "All details about category document")
public class BaseUnitModelView extends BaseModelView<Long> implements ExposableUnitModelView {


}
