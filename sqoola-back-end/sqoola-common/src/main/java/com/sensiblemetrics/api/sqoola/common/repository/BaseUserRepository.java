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

import com.wildbeeslabs.api.rest.common.model.BaseUserEntity;
import com.wildbeeslabs.api.rest.common.model.UserAccountStatusInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link BaseUserEntity} repository declaration
 *
 * @param <E>
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@NoRepositoryBean
public interface BaseUserRepository<E extends BaseUserEntity, ID extends Serializable> extends AuditModelRepository<E, ID>, PagingAndSortingRepository<E, ID> {

    /**
     * Get user entities {@link BaseUserEntity} by uuId
     *
     * @param uuId - user unique ID
     * @return user entities {@link BaseUserEntity} in optional
     * container {@link Optional}
     */
    //@CacheEvict("byUsername")
    @Cacheable(value = "userFindCache", key = "#uuId", sync = true, cacheNames = {"userFindCache"})
    Optional<? extends E> findByUuId(final UUID uuId);

    /**
     * Get user entities {@link BaseUserEntity} by login (case insensitive)
     *
     * @param login - user login
     * @return user entities {@link BaseUserEntity} in optional container
     * {@link Optional}
     */
    //@CacheEvict("byUsername")
    @Cacheable(value = "userFindCache", key = "#login", sync = true, cacheNames = {"userFindCache"})
    Optional<? extends E> findByLoginIgnoreCase(final String login);

    /**
     * Get user entities {@link BaseUserEntity} by login (case insensitive) using
     * the projection user entities type
     *
     * @param login      - user login
     * @param projection - user entities class {@link BaseUserEntity}
     * @return user entities {@link BaseUserEntity} in optional container
     * {@link Optional}
     */
    Optional<? extends E> findByLoginIgnoreCase(final String login, final Class<? extends E> projection);

    /**
     * Get list of user entities {@link BaseUserEntity} by status
     * {@link UserStatusInfo.StatusType}
     *
     * @param status - user status
     * @return list of user entities {@link BaseUserEntity}
     */
    List<? extends E> findByStatus(final UserAccountStatusInfo.StatusType status);

    List<E> findByName(final String name);
}
