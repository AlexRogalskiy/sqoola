/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
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
package com.sensiblemetrics.api.sqoola.common.service;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseInfoModelEntity;
import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import com.wildbeeslabs.api.rest.common.model.BaseDictionaryInfoEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * {@link BaseInfoModelEntity} DAO service declaration
 *
 * @param <E>  type of base model {@link BaseModelEntity}
 * @param <ID> type of base document identifier {@link Serializable}
 */
public interface BaseInfoModelDaoService<E extends BaseInfoModelEntity<ID>, ID extends Serializable> extends BaseDaoService<E, ID> {

    /**
     * Get information entities {@link BaseInfoModelEntity} by prefix name
     *
     * @param prefix - information prefix name
     * @return information entities {@link BaseInfoModelEntity} in optional container
     * {@link Optional}
     */
    default Optional<? extends E> findByPrefix(final String prefix) {
        return getRepository().findByPrefix(prefix);
    }

    /**
     * Get list of information entities {@link BaseInfoModelEntity} by dictionary
     * entities {@link BaseDictionaryInfoEntity}
     *
     * @param dictionary - dictionary entities
     * @return list of information entities {@link BaseInfoModelEntity}
     */
    default List<? extends E> findByDictionary(final BaseDictionaryInfoEntity dictionary) {
        return getRepository().findByDictionary(dictionary);
    }
}
