/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.service.dao;

import com.wildbeeslabs.api.rest.common.model.BaseEntity;
import com.wildbeeslabs.api.rest.common.repository.BaseRepository;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * Base Entity REST Application Service declaration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <R>
 */
public interface IBaseEntityService<T extends BaseEntity, R extends BaseRepository<T>> extends IService<T, R> {

    /**
     * Get list of entities {@link BaseEntity} created by user / owner name
     *
     * @param createdBy - user / owner name of created entities
     * @return list of entities {@link BaseEntity} in concurrent container
     * {@link CompletableFuture}
     */
    default CompletableFuture<List<? extends T>> findByCreatedBy(final String createdBy) {
        return getRepository().findByCreatedBy(createdBy);
    }

    /**
     * Get list of entities {@link BaseEntity} modified by user / owner name
     *
     * @param modifiedBy - user / owner name of modified entities
     * @return list of entities {@link BaseEntity} in concurrent container
     * {@link CompletableFuture}
     */
    default CompletableFuture<List<? extends T>> findByModifiedBy(final String modifiedBy) {
        return getRepository().findByModifiedBy(modifiedBy);
    }

    /**
     * Get list of entities {@link BaseEntity} created within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo - end date of requested period (including)
     * @return list of entities {@link BaseEntity} in concurrent container
     * {@link CompletableFuture}
     */
    default CompletableFuture<List<? extends T>> findByCreatedAtBetween(final Date dateFrom, final Date dateTo) {
        return getRepository().findByCreatedAtBetween(dateFrom, dateTo);
    }

    /**
     * Get list of entities {@link BaseEntity} created within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo - end date of requested period (including)
     * @return list of entities {@link BaseEntity} in concurrent container
     * {@link CompletableFuture}
     */
    default CompletableFuture<List<? extends T>> findByModifiedAtBetween(final Date dateFrom, final Date dateTo) {
        return getRepository().findByModifiedAtBetween(dateFrom, dateTo);
    }
}
