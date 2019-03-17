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
package com.ubs.network.api.gateway.core.service.interfaces;

import com.wildbeeslabs.api.rest.common.service.interfaces.IBaseEntityService;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.common.service.interfaces.IJpaBaseService;
import com.wildbeeslabs.api.rest.subscription.repository.SubscriptionRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * Subscription REST Application Service declaration {@link Subscription}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
public interface ISubscriptionService<T extends Subscription> extends IJpaBaseService<T, Long>, IBaseEntityService<T, SubscriptionRepository<T>> {

    /**
     * Get subscription entities by title
     *
     * @param title - subscription title
     * @return list of subscription entities
     */
    Optional<? extends T> findByTitle(final String title);

    /**
     * Get list of subscription entities by title pattern
     *
     * @param titlePattern - subscription title pattern
     * @return list of subscription entities
     */
    List<? extends T> findByTitlePattern(final String titlePattern);

    /**
     * Get list of subscription entities by type
     *
     * @param status - subscription status type
     * @return list of subscription entities
     */
    List<? extends T> findByStatus(final SubscriptionStatusInfo.StatusType status);

    /**
     * Get list of subscription entities by user ID
     *
     * @param userId - user identifier
     * @return list of subscription entities
     */
    List<? extends T> findByUserId(final Long userId);
}
