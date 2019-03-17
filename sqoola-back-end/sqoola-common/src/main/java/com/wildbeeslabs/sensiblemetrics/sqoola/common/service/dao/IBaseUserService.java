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

import com.wildbeeslabs.api.rest.common.model.BaseUserEntity;
import com.wildbeeslabs.api.rest.common.model.UserAccountStatusInfo;
import com.wildbeeslabs.api.rest.common.repository.BaseUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * BaseUser REST Application Service declaration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <R>
 */
public interface IBaseUserService<T extends BaseUserEntity, R extends BaseUserRepository<T>> extends IBaseEntityService<T, R> {

    /**
     * Get user entities {@link BaseUserEntity} by uuId
     *
     * @param uuId - user unique ID
     * @return user entities {@link BaseUserEntity} in optional container
     * {@link Optional}
     */
    default Optional<? extends T> findByUuId(final UUID uuId) {
        return getRepository().findByUuId(uuId);
    }

    /**
     * Get user entities {@link BaseUserEntity} by login
     *
     * @param login - user login
     * @return user entities {@link BaseUserEntity} in optional container
     * {@link Optional}
     */
    default Optional<? extends T> findByLogin(final String login) {
        return getRepository().findByLoginIgnoreCase(login);
    }

    /**
     * Get list of user entities {@link BaseUserEntity} by status
     * {@link UserStatusInfo.StatusType}
     *
     * @param status - user status
     * @return list of user entities {@link BaseUserEntity}
     */
    default List<? extends T> findByStatus(final UserAccountStatusInfo.StatusType status) {
        return getRepository().findByStatus(status);
    }
}
