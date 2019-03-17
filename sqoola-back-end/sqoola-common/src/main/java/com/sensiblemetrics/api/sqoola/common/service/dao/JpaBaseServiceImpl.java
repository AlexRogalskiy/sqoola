/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
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

import com.wildbeeslabs.api.rest.common.service.interfaces.IJpaBaseService;

import com.wildbeeslabs.api.rest.common.model.IBaseEntity;
import com.wildbeeslabs.api.rest.common.repository.JpaBaseRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * JPA Base REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <ID>
 * @param <R>
 */
@Transactional
public abstract class JpaBaseServiceImpl<T extends IBaseEntity, ID extends Serializable, R extends JpaBaseRepository<T, ID>> implements IJpaBaseService<T, ID> {

    @Autowired
    private R repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<? extends T> findById(final ID id) {
        return Optional.<T>ofNullable(getRepository().findOne(id));
    }

    @Override
    public void create(final T item) {
        item.setId(null);
        save(item);
    }

    @Override
    public void save(final T item) {
        getRepository().save(item);
    }

    @Override
    public void update(final T item) {
        save(item);
    }

    @Override
    public void merge(final T itemTo, final T itemFrom) {
        itemFrom.setId(itemTo.getId());
        update(itemFrom);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public void deleteById(final ID id) {
        getRepository().delete(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public void delete(final T item) {
        getRepository().delete(item);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public void delete(final List<? extends T> items) {
        getRepository().delete(items);
    }

    @Override
    @Transactional(readOnly = true)
    public List<? extends T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends T> findAll(final Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(final ID id) {
        return getRepository().exists(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isExist(final T item) {
        return getRepository().exists(Example.of(item));
    }

    protected R getRepository() {
        return repository;
    }
}
