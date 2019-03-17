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
package com.sensiblemetrics.api.sqoola.common.repository;

import com.wildbeeslabs.api.rest.common.model.BaseUserAccountEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * BaseUserAccount REST Application storage repository to manage
 * {@link BaseUserAccountEntity} instances
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@NoRepositoryBean
public interface BaseUserAccountRepository<T extends BaseUserAccountEntity> extends BaseRepository<T> {

    /**
     * Get user account entities {@link BaseUserAccountEntity} by uuId
     *
     * @param uuId - user unique ID
     * @return user account entities {@link BaseUserAccountEntity} in optional
     * container {@link Optional}
     */
    //@CacheEvict("byUsername")
    @Cacheable(value = "userAccountFindCache", key = "#uuId", sync = true, cacheNames = {"userAccountFindCache"})
    Optional<? extends T> findByUuId(final UUID uuId);

    /**
     * Get user account entities {@link BaseUserAccountEntity} by user name
     *
     * @param username - user name
     * @return user account entities {@link BaseUserAccountEntity} in optional
     * container {@link Optional}
     */
    //@CacheEvict("byUsername")
    @Cacheable(value = "userAccountFindCache", key = "#username", sync = true, cacheNames = {"userAccountFindCache"})
    Optional<? extends T> findByUsername(final String username);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} by
     * enabled status
     *
     * @param isEnabled - user enabled status
     * @return list of user account entities {@link BaseUserAccountEntity}
     */
    List<? extends T> findByIsEnabled(final Boolean isEnabled);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} activated
     * before requested date
     *
     * @param date - requested date (excluding)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.activatedAt <= ?1")
    @Async
    CompletableFuture<List<? extends T>> findByActivatedAtLessThan(final Date date);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} activated
     * after requested date
     *
     * @param date - requested date (excluding)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.activatedAt > ?1")
    @Async
    CompletableFuture<List<? extends T>> findByActivatedAtGreaterThan(final Date date);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} activated
     * within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo - end date of requested period (including)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.activatedAt > ?1 AND e.activatedAt <= ?2")
    @Async
    CompletableFuture<List<? extends T>> findByActivatedAtBetween(final Date dateFrom, final Date dateTo);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} accessed
     * before requested date
     *
     * @param date - requested date (excluding)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.accessedAt <= ?1")
    @Async
    CompletableFuture<List<? extends T>> findByAccessedAtLessThan(final Date date);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} accessed
     * after requested date
     *
     * @param date - requested date (excluding)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.accessedAt > ?1")
    @Async
    CompletableFuture<List<? extends T>> findByAccessedAtGreaterThan(final Date date);

    /**
     * Get list of user account entities {@link BaseUserAccountEntity} accessed
     * within requested period
     *
     * @param dateFrom - start date of requested period (excluding)
     * @param dateTo - end date of requested period (including)
     * @return list of user account entities {@link BaseUserAccountEntity} with
     * asynchronous iterator {@link CompletableFuture}
     */
    //@Query("SELECT e FROM #{#entityName} e WHERE e.accessedAt > ?1 AND e.accessedAt <= ?2")
    @Async
    CompletableFuture<List<? extends T>> findByAccessedAtBetween(final Date dateFrom, final Date dateTo);
}
