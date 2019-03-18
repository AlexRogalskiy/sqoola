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
package com.sensiblemetrics.api.sqoola.common.controller.category.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sensiblemetrics.api.sqoola.common.controller.wrapper.GeneralResult;
import com.sensiblemetrics.api.sqoola.common.model.dao.CategoryEntity;
import com.sensiblemetrics.api.sqoola.common.model.dto.BaseCategoryView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * {@link CategoryEntity} result entity
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JacksonXmlRootElement(localName = "categories")
public class CategoryResult extends GeneralResult<BaseCategoryView> {

    /**
     * Default {@link CategoryResult} constructor with input {@link BaseCategoryView} key
     *
     * @param key - initial input {@link BaseCategoryView} key
     */
    public CategoryResult(final BaseCategoryView key) {
        super(key);
    }

    /**
     * Default {@link CategoryResult} constructor with input {@link BaseCategoryView} key and error message {@link String}
     *
     * @param key          - initial input {@link BaseCategoryView} key
     * @param errorMessage - initial input error message {@link String}
     */
    public CategoryResult(final BaseCategoryView key, final String errorMessage) {
        super(key, errorMessage);
    }
}
