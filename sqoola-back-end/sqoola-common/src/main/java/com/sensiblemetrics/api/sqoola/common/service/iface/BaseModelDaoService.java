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
package com.sensiblemetrics.api.sqoola.common.service.iface;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;

import java.io.Serializable;
import java.util.Optional;

/**
 * {@link BaseModelEntity} DAO service declaration
 *
 * @param <E>  type of base model {@link BaseModelEntity}
 * @param <ID> type of base document identifier {@link Serializable}
 */
public interface BaseModelDaoService<E extends BaseModelEntity<ID>, ID extends Serializable> extends AuditModelDaoService<E, ID> {

    /**
     * Get information entities {@link BaseInfoEntity} by prefix name
     *
     * @param prefix - information prefix name
     * @return information entities {@link BaseInfoEntity} in optional container
     * {@link Optional}
     */
    Optional<? extends E> findByPrefix(final String prefix);

    /**
     * Get list of information entities {@link BaseInfoEntity} by dictionary
     * entities {@link BaseDictionaryInfoEntity}
     *
     * @param dictionary - dictionary entities
     * @return list of information entities {@link BaseInfoEntity}
     */
    Iterable<? extends E> findByDictionary(final BaseDictionaryInfoEntity dictionary);
}
