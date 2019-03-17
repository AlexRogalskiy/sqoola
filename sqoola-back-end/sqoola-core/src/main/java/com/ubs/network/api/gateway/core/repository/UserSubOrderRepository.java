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
package com.ubs.network.api.gateway.core.repository;

import com.wildbeeslabs.api.rest.common.repository.BaseRepository;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * UserSubOrder REST Application storage repository to manage
 * {@link UserSubOrder} instances.
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Repository
public interface UserSubOrderRepository<T extends UserSubOrder> extends JpaBaseRepository<T, UserSubOrder.UserSubOrderId>, BaseRepository<T> {

    /**
     * Default query to find orders by user id
     */
    public final static String FIND_ORDER_BY_USER_ID_QUERY = "SELECT o FROM UserSubOrder o WHERE o.id.user.id = :userId";

    /**
     * Default query to find orders by user entities
     */
    public final static String FIND_ORDER_BY_USER_QUERY = "SELECT o FROM UserSubOrder o WHERE o.id.user = :user";

    /**
     * Default query to find orders by subscription id
     */
    public final static String FIND_ORDER_BY_SUB_ID_QUERY = "SELECT o FROM UserSubOrder o WHERE o.id.subscription.id = :subscriptionId";

    /**
     * Default query to find orders by subscription entities
     */
    public final static String FIND_ORDER_BY_SUB_QUERY = "SELECT o FROM UserSubOrder o WHERE o.id.subscription = :subscription";

    /**
     * Get list of subscription orders by user ID using the {@link Query}
     * annotation
     *
     * @param userId - user identifier
     * @return list of subscription orders {@link UserSubOrder}
     */
    @Query(FIND_ORDER_BY_USER_ID_QUERY)
    List<? extends T> findByUserId(@Param("userId") final Long userId);

    /**
     * Get list of subscription orders by user entities {@link User} using the
     * {@link Query} annotation
     *
     * @param user - user entities
     * @return list of subscription orders {@link UserSubOrder}
     */
    @Query(FIND_ORDER_BY_USER_QUERY)
    List<? extends T> findByUser(@Param("user") final User user);

    /**
     * Get list of subscription orders by subscription ID using the
     * {@link Query} annotation
     *
     * @param subscriptionId - subscription identifier
     * @return list of subscription orders {@link UserSubOrder}
     */
    @Query(FIND_ORDER_BY_SUB_ID_QUERY)
    List<? extends T> findBySubscriptionId(@Param("subscriptionId") final Long subscriptionId);

    /**
     * Get list of subscription orders by subscription entities {@link Subscription}
     * {@link Subscription} using the {@link Query} annotation
     *
     * @param subscription - subscription entities
     * @return list of subscription orders {@link UserSubOrder}
     */
    @Query(FIND_ORDER_BY_SUB_QUERY)
    List<? extends T> findBySubscription(@Param("subscription") final Subscription subscription);

    /**
     * Get list of subscription orders by subscribed date period using the
     * {@link Query} annotation
     *
     * @param dateFrom - start date of period
     * @param dateTo - end date of period
     * @return list of subscription orders {@link UserSubOrder}
     */
    List<? extends T> findByStartedAtBetween(final Date dateFrom, final Date dateTo);

    /**
     * Get list of subscription orders by subscribed date before using the
     * {@link Query} annotation
     *
     * @param date - request date (including)
     * @return list of subscription orders {@link UserSubOrder}
     */
    List<? extends T> findByStartedAtLessThanEqual(final Date date);

    /**
     * Get list of subscription orders by subscribed date after using the
     * {@link Query} annotation
     *
     * @param date - request date (excluding)
     * @return list of subscription orders {@link UserSubOrder}
     */
    List<? extends T> findByStartedAtGreaterThan(final Date date);
}
