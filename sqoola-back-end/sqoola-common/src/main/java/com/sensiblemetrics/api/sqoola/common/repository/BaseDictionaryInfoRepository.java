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
package com.sensiblemetrics.api.sqoola.common.repository;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * {@link BaseDictionaryInfoEntity} repository declaration
 *
 * @param <E>
 * @param <ID>
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Repository
public interface BaseDictionaryInfoRepository<E extends BaseDictionaryInfoEntity<ID>, ID extends Serializable> extends BaseModelRepository<E, ID> {

    /**
     * Get dictionary information entities {@link BaseDictionaryInfoEntity} by
     * prefix name
     *
     * @param prefix - information prefix name
     * @return dictionary information entities {@link BaseDictionaryInfoEntity} in
     * optional container {@link Optional}
     */
    Optional<? extends E> findByPrefix(final String prefix);

    /**
     * Get dictionary information entities {@link BaseDictionaryInfoEntity} by
     * code
     *
     * @param code - information code
     * @return dictionary information entities {@link BaseDictionaryInfoEntity} in
     * optional container {@link Optional}
     */
    Optional<? extends E> findByCode(final String code);

    /**
     * Get list of dictionary information entities
     * {@link BaseDictionaryInfoEntity} by name pattern
     *
     * @param namePattern - information name pattern
     * @return list of dictionary information entities
     * {@link BaseDictionaryInfoEntity}
     */
    Iterable<? extends E> findByNameLike(final String namePattern);
}
