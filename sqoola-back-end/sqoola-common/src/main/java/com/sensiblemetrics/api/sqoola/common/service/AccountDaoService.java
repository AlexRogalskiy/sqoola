/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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

import com.sensiblemetrics.api.sqoola.common.model.dao.AccountEntity;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * {@link BaseAccount} service declaration
 *
 * @param <E>
 * @param <ID>
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public interface AccountDaoService<E extends BaseAccount<ID>, ID extends Serializable> extends BaseModelDaoService<E, Long> {

    /**
     * Default service ID
     */
    String SERVICE_ID = "accountService";

    /**
     * Get user account entities {@link AccountEntity} by user name
     *
     * @param username - user name
     * @return user account entities {@link AccountEntity} in optional
     * container {@link Optional}
     */
    Optional<? extends E> findByUsername(final String username);


    /**
     * Get user account entities {@link AccountEntity} by uuId
     *
     * @param uuId - user unique ID
     * @return user account entities {@link AccountEntity} in optional
     * container {@link Optional}
     */
    Optional<? extends E> findByUuId(final UUID uuId);
}
