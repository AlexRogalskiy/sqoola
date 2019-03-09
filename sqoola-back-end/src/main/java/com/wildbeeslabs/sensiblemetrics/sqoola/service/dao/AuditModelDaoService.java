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
package com.wildbeeslabs.sensiblemetrics.sqoola.service.dao;

import com.wildbeeslabs.sensiblemetrics.sqoola.model.dao.AuditModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * {@link AuditModel} service declaration
 *
 * @param <E>  type of audit document {@link AuditModel}
 * @param <ID> type of audit document identifier {@link Serializable}
 */
public interface AuditModelDaoService<E extends AuditModel, ID extends Serializable> extends BaseDaoService<E, ID> {

    List<? extends E> findByCreatedBetween(final Date dateFrom, final Date dateTo);

    List<? extends E> findByChangedBetween(final Date dateFrom, final Date dateTo);
}
