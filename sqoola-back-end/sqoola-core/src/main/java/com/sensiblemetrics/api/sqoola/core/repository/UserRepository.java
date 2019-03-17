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

import com.wildbeeslabs.api.rest.common.repository.BaseUserRepository;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * User REST Application storage repository to manage {@link User} instances.
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Repository
public interface UserRepository<T extends User> extends JpaBaseRepository<T, Long>, BaseUserRepository<T> {

    /**
     * Default query to find users by subscription id
     */
    public final static String FIND_USER_BY_SUB_ID_QUERY = "SELECT o.id.user FROM UserSubOrder o WHERE o.id.subscription.id = :subscriptionId";

    /**
     * Default query to find subscribed users by subscription status (optional)
     * and date after (excluding)
     */
    public final static String FIND_USER_BY_OPT_SUB_STATUS_AND_SUB_DATE_AFTER_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE (:subStatus IS NULL OR o.id.subscription.status.statusType = :subStatus) AND o.startedAt > :subDate";

    /**
     * Default query to find subscribed users by subscription status (optional)
     * and date before (including)
     */
    public final static String FIND_USER_BY_OPT_SUB_STATUS_AND_SUB_DATE_BEFORE_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE (:subStatus IS NULL OR o.id.subscription.status.statusType = :subStatus) AND o.startedAt <= :subDate";

    /**
     * Default query to find subscribed users by subscription status (optional),
     * date (optional), date sort order (optional)
     */
    public final static String FIND_USER_BY_OPT_SUB_STATUS_AND_OPT_SUB_DATE_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE (:subStatus IS NULL OR o.id.subscription.status.statusType = :subStatus) AND (:subDate IS NULL OR ((:dateTypeOrder = 'BEFORE' AND o.startedAt <= :subDate) OR o.startedAt >= :subDate))";

    /**
     * Default query to find subscribed users by date after (excluding)
     */
    public final static String FIND_USER_BY_SUB_DATE_AFTER_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE o.startedAt > :subDate";

    /**
     * Default query to find subscribed users by date before (including)
     */
    public final static String FIND_USER_BY_SUB_DATE_BEFORE_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE o.startedAt <= :subDate";

    /**
     * Default query to find subscribed users by date
     */
    public final static String FIND_USER_BY_SUB_DATE_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE ((:order = 'BEFORE' AND o.startedAt <= :subDate) OR o.startedAt >= :subDate)";

    /**
     * Default query to find subscribed users by date between (including)
     */
    public final static String FIND_USER_BY_SUB_DATE_BETWEEN_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE o.startedAt BETWEEN :startSubDate AND :endSubDate";

    /**
     * Default query to find subscribed users by subscription status
     */
    public final static String FIND_USER_BY_SUB_STATUS_QUERY = "SELECT DISTINCT o.id.user FROM UserSubOrder o WHERE o.id.subscription.status.statusType = :subStatus";

    /**
     * Get list of user entities by subscription status (optional)
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} and date before
     * using the {@link Query} annotation
     *
     * @param subDate - request date (including)
     * @param subStatus - subscription type
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_OPT_SUB_STATUS_AND_SUB_DATE_BEFORE_QUERY)
    List<? extends T> findByOptionalSubscriptionStatusAndDateBefore(@Param("subDate") final Date subDate, @Param("subStatus") final Optional<SubscriptionStatusInfo.StatusType> subStatus);

    /**
     * Get list of user entities by subscription status (optional)
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} and date after
     * using the {@link Query} annotation
     *
     * @param subDate - request date (excluding)
     * @param subStatus - subscription type
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_OPT_SUB_STATUS_AND_SUB_DATE_AFTER_QUERY)
    List<? extends T> findByOptionalSubscriptionStatusAndDateAfter(@Param("subDate") final Date subDate, @Param("subStatus") final Optional<SubscriptionStatusInfo.StatusType> subStatus);

    /**
     * Get list of user entities by subscription status (optional)
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} and date using the
     * {@link Query} annotation
     *
     * @param subDate - request date (excluding)
     * @param subStatus - subscription type
     * @param dateTypeOrder - order of comparison (date after / before)
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_OPT_SUB_STATUS_AND_OPT_SUB_DATE_QUERY)
    List<? extends T> findByOptionalSubscriptionStatusAndOptionalDate(@Param("subDate") final Optional<Date> subDate, @Param("subStatus") final Optional<SubscriptionStatusInfo.StatusType> subStatus, @Param("dateTypeOrder") final String dateTypeOrder);

    /**
     * Get list of user entities by subscription date before (including) using
     * the {@link Query} annotation
     *
     * @param subDate - request date (including)
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_DATE_BEFORE_QUERY)
    List<? extends T> findBySubscriptionDateBefore(@Param("subDate") final Date subDate);

    /**
     * Get list of user entities by subscription date after (excluding) using
     * the {@link Query} annotation
     *
     * @param subDate - request date (excluding)
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_DATE_AFTER_QUERY)
    List<? extends T> findBySubscriptionDateAfter(@Param("subDate") final Date subDate);

    /**
     * Get list of user entities by subscription date (after/before) using the
     * {@link Query} annotation
     *
     * @param subDate - subscription date (excluding)
     * @param order - order of comparison (date after / before)
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_DATE_QUERY)
    List<? extends T> findBySubscriptionDate(@Param("subDate") final Date subDate, @Param("order") final String order);

    /**
     * Get list of user entities by subscription type
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} using the
     * {@link Query} annotation
     *
     * @param subStatus - subscription type
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_STATUS_QUERY)
    List<? extends T> findBySubscriptionStatus(@Param("subStatus") final SubscriptionStatusInfo.StatusType subStatus);

    /**
     * Get list of user entities by subscription ID using the {@link Query}
     * annotation
     *
     * @param subscriptionId - subscription identifier
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_ID_QUERY)
    List<? extends T> findBySubscriptionId(@Param("subscriptionId") final Long subscriptionId);

    /**
     * Get list of user entities by subscription date between (including) using
     * the {@link Query} annotation
     *
     * @param startSubDate - subscription start date (including)
     * @param endSubDate - subscription end date (including)
     * @return list of user entities {@link User}
     */
    @Query(FIND_USER_BY_SUB_DATE_BETWEEN_QUERY)
    List<? extends T> findBySubscriptionDateBetween(@Param("startSubDate") final Date startSubDate, @Param("endSubDate") final Date endSubDate);
}
