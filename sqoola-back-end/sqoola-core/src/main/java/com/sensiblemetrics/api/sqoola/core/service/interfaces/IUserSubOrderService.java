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
import com.wildbeeslabs.api.rest.common.service.interfaces.IJpaBaseService;

import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.subscription.repository.UserSubOrderRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * UserSubOrder REST Application Service declaration for {@link UserSubOrder}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
public interface IUserSubOrderService<T extends UserSubOrder> extends IJpaBaseService<T, UserSubOrder.UserSubOrderId>, IBaseEntityService<T, UserSubOrderRepository<T>> {

    /**
     * Get list of subscription orders by user entities
     *
     * @param user - user entities
     * @return list of subscription orders
     */
    List<? extends T> findByUser(final User user);

    /**
     * Get list of subscription orders by subscription entities
     *
     * @param subscription - subscription entities
     * @return list of subscription orders
     */
    List<? extends T> findBySubscription(final Subscription subscription);

    /**
     * Get list of subscription orders by user and subscription entities
     *
     * @param user - user entities
     * @param subscription - subscription entities
     * @return subscription order
     */
    Optional<? extends T> findByUserAndSubscription(final User user, final Subscription subscription);

    /**
     * Get list of subscription orders by date range (start date / end date)
     *
     * @param dateFrom - start date of range
     * @param dateTo - end date of range
     * @return list of subscription orders
     */
    List<? extends T> findByStartedAtBetween(final Date dateFrom, final Date dateTo);
}
