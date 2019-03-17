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
package com.sensiblemetrics.api.sqoola.common.repository;

import com.sensiblemetrics.api.sqoola.common.model.dao.AuditModel;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * {@link AuditModel} repository declaration {@link BaseJpaRepository}
 *
 * @param <E>  type of audit model
 * @param <ID> type of audit model identifier
 */
@NoRepositoryBean
public interface AuditModelRepository<E extends AuditModel, ID extends Serializable> extends BaseJpaRepository<E, ID> {

    /**
     * Get list of entities {@link AuditModel} created before requested date
     *
     * @param date - requested date (excluding)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.created <= ?1")
    @Async
    CompletableFuture<List<? extends E>> findByCreatedLessThanEqual(final Date date);

    /**
     * Get list of entities {@link AuditModel} created after requested date
     *
     * @param date - requested date (excluding)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.created > ?1")
    @Async
    CompletableFuture<List<? extends E>> findByCreatedGreaterThan(final Date date);

    /**
     * Get list of entities {@link AuditModel} created within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo   - end date of requested period (including)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.created > ?1 AND e.created <= ?2")
    @Async
    CompletableFuture<List<? extends E>> findByCreatedBetween(final Date dateFrom, final Date dateTo);

    /**
     * Get list of entities {@link AuditModel} created by user / owner name
     *
     * @param createdBy - user / owner name of created entities
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.createdBy = ?1")
    @Async
    CompletableFuture<List<? extends E>> findByCreatedBy(final String createdBy);

    /**
     * Get list of entities {@link AuditModel} created by user / owner name
     * pattern
     *
     * @param createdByPattern - user / owner name pattern of created entities
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.createdBy LIKE ?1")
    @Async
    CompletableFuture<List<? extends E>> findByCreatedByLike(final String createdByPattern);

    /**
     * Get list of entities {@link AuditModel} modified before requested date
     *
     * @param date - requested date (excluding)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.changed <= ?1")
    @Async
    CompletableFuture<List<? extends E>> findByChangedLessThan(final Date date);

    /**
     * Get list of entities {@link AuditModel} modified after requested date
     *
     * @param date - requested date (excluding)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.changed > ?1")
    @Async
    CompletableFuture<List<? extends E>> findByChangedGreaterThan(final Date date);

    /**
     * Get list of entities {@link AuditModel} modified within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo   - end date of requested period (including)
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.changed > ?1 AND e.changed <= ?2")
    @Async
    CompletableFuture<List<? extends E>> findByChangedBetween(final Date dateFrom, final Date dateTo);

    /**
     * Get list of entities {@link AuditModel} modified by user / owner name
     *
     * @param changedBy - user / owner name of modified entities
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.changedBy = ?1")
    @Async
    CompletableFuture<List<? extends E>> findByChangedBy(final String changedBy);

    /**
     * Get list of entities {@link AuditModel} modified by user / owner name
     * pattern
     *
     * @param changedByPattern - user / owner name pattern of modified entities
     * @return list of entities {@link AuditModel} with asynchronous iterator
     * {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.changedBy LIKE ?1")
    @Async
    CompletableFuture<List<? extends E>> findByChangedByLike(final String changedByPattern);
}
