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
package com.sensiblemetrics.api.sqoola.common.service.dao;

import com.wildbeeslabs.api.rest.common.model.UserAccountStatusInfo;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * BaseUser REST Application Service declaration
 *
 * @param <E>
 * @param <ID>
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public interface BaseUserDaoService<E extends BaseUser, ID extends Serializable> extends IBaseEntityService<E, ID> {

    /**
     * Get user entities {@link BaseUser} by uuId
     *
     * @param uuId - user unique ID
     * @return user entities {@link BaseUser} in optional container
     * {@link Optional}
     */
    default Optional<? extends E> findByUuId(final UUID uuId) {
        return getRepository().findByUuId(uuId);
    }

    /**
     * Get user entities {@link BaseUser} by login
     *
     * @param login - user login
     * @return user entities {@link BaseUser} in optional container
     * {@link Optional}
     */
    default Optional<? extends E> findByLogin(final String login) {
        return getRepository().findByLoginIgnoreCase(login);
    }

    /**
     * Get list of user entities {@link BaseUser} by status
     * {@link UserStatusInfo.StatusType}
     *
     * @param status - user status
     * @return list of user entities {@link BaseUser}
     */
    default Iterable<? extends E> findByStatus(final UserAccountStatusInfo.StatusType status) {
        return getRepository().findByStatus(status);
    }
}
