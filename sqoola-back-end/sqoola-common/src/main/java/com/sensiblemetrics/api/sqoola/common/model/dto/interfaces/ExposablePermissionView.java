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
package com.sensiblemetrics.api.sqoola.common.model.dto.interfaces;

import com.sensiblemetrics.api.sqoola.common.model.dto.BasePermissionView;

import java.io.Serializable;

/**
 * {@link BasePermissionView} model view definition
 */
public interface ExposablePermissionView extends Serializable {

    /**
     * Default dto ID
     */
    String VIEW_ID = "permission";

    /**
     * Default field names
     */
    String NAME_FIELD_NAME = "name";
    String SHORT_DESCRIPTION_FIELD_NAME = "shortDescription";
    String LONG_DESCRIPTION_FIELD_NAME = "longDescription";
    String PRICE_DESCRIPTION_FIELD_NAME = "priceDescription";
    String CATALOG_NUMBER_FIELD_NAME = "catalogNumber";
    String PAGE_TITLE_FIELD_NAME = "pageTitle";
    String AVAILABLE_FIELD_NAME = "inStock";
}
