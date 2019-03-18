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
package com.sensiblemetrics.api.sqoola.common.model.dao.interfaces;

/**
 * Persistable order model definition
 */
public interface PersistableOrder {

    /**
     * Default document ID
     */
    String MODEL_ID = "OrderEntity";
    /**
     * Default table name
     */
    String TABlE_NAME = "orders";

    /**
     * Default field names
     */
    String ID_FIELD_NAME = "id";
    String ORDER_ID_FIELD_NAME = "orderId";
    String TITLE_FIELD_NAME = "title";
    String DESCRIPTION_FIELD_NAME = "description";
    String CLIENT_NAME_FIELD_NAME = "clientName";
    String CLIENT_MOBILE_FIELD_NAME = "clientMobile";
    String PRODUCTS_FIELD_NAME = "products";
    /**
     * Default reference field names
     */
    String ORDERS_REF_FIELD_NAME = "orders";
}
