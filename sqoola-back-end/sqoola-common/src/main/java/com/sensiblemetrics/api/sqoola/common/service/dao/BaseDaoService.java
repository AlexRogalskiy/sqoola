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
package com.sensiblemetrics.api.sqoola.common.service.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;

/**
 * Base DAO service declaration
 *
 * @param <E>  type of model {@link Serializable}
 * @param <ID> type of model identifier {@link Serializable}
 */
public interface BaseDaoService<E extends Serializable, ID extends Serializable> {

    <E extends Serializable> Iterable<? extends E> findAll();

    <E extends Serializable, ID extends Serializable> Iterable<? extends E> findAll(final Iterable<ID> target);

    <E extends Serializable> Page<? extends E> findAll(final Pageable pageable);

    <E extends Serializable, ID extends Serializable> Optional<E> find(final ID id);

    <E extends Serializable> E save(final E entity);

    //void saveOrUpdate(final E target, final Class<? extends E> clazz);

    <ID extends Serializable> boolean exists(final ID id);

    <E extends Serializable> Iterable<? extends E> saveAll(final Iterable<? extends E> target);

    <E extends Serializable> void delete(final E target);

    <E extends Serializable> void deleteAll(final Iterable<? extends E> target);

    void deleteAll();
}
