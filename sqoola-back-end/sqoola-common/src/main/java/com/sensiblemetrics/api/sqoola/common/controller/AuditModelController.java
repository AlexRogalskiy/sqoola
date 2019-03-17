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
package com.sensiblemetrics.api.sqoola.common.controller;

import com.sensiblemetrics.api.sqoola.common.model.dto.AuditModelView;
import com.sensiblemetrics.api.sqoola.common.model.dao.AuditModel;

import java.io.Serializable;

/**
 * Audit {@link BaseController} declaration
 *
 * @param <E>  type of audit document model {@link AuditModel}
 * @param <T>  type of audit document view model {@link AuditModelView}
 * @param <ID> type of audit document identifier {@link Serializable}
 * @author Alex
 * @version 1.0.0
 */
public interface AuditModelController<E extends AuditModel, T extends AuditModelView, ID extends Serializable> extends BaseController<E, T, ID> {

    /**
     * Default token expire period
     */
    int DEFAULT_TOKEN_EXPIRE_PERIOD = 5;
    /**
     * Default rate limit
     */
    int DEFAULT_RATE_LIMIT = 5000;

    /**
     * The HTTP {@code X-Total-Elements} header field name
     */
    String DEFAULT_TOTAL_ELEMENTS_HEADER = "X-Total-Elements";
    /**
     * The HTTP {@code X-Expires-After} header field name
     */
    String DEFAULT_EXPIRES_AFTER_HEADER = "X-Expires-After";
    /**
     * The HTTP {@code X-Rate-Limit} header field name
     */
    String DEFAULT_RATE_LIMIT_HEADER = "X-Rate-Limit";
}
