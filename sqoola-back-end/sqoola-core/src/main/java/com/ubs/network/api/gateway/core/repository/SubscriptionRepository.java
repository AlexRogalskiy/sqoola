
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
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * Subscription REST Application storage repository to manage
 * {@link Subscription} instances.
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Repository
public interface SubscriptionRepository<T extends Subscription> extends JpaBaseRepository<T, Long>, BaseRepository<T> {

    /**
     * Default query to find subscriptions by user id
     */
    public final static String FIND_SUB_BY_USER_ID_QUERY = "SELECT o.id.subscription FROM UserSubOrder o WHERE o.id.user.id = :userId";

    /**
     * Default query to find subscriptions by status
     */
    public final static String FIND_SUB_BY_STATUS_QUERY = "SELECT s FROM Subscription s WHERE s.status.statusType = :status";

    /**
     * Get subscription entities by title (case insensitive)
     *
     * @param title - subscription title
     * @return subscription entities {@link Subscription}
     */
    Optional<? extends T> findByTitleIgnoreCase(final String title);

    /**
     * Get list of subscription entities by title pattern
     *
     * @param titlePattern - subscription title pattern
     * @return list of subscription entities {@link Subscription}
     */
    List<? extends T> findByTitleLike(final String titlePattern);

    /**
     * Get list of subscription entities by title pattern using {@link Slice}
     * counting a maximum number of {@link Pageable#getPageSize()} subscriptions
     * matching given criteria starting at {@link Pageable#getOffset()} without
     * prior count of the total number of elements available
     *
     * @param titlePattern - subscription title pattern
     * @param pageable - pagination entities
     * @return list of subscription entities {@link Subscription}
     */
    Slice<? extends T> findByTitleLike(final String titlePattern, final Pageable pageable);

    /**
     * Get list of subscription entities by type
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} using the
     * {@link Query} annotation
     *
     * @param status - subscription status
     * @return list of subscription entities {@link Subscription}
     */
    @Query(FIND_SUB_BY_STATUS_QUERY)
    List<? extends T> findByStatus(@Param("status") final SubscriptionStatusInfo.StatusType status);

    /**
     * Get list of subscription entities by user ID using the {@link Query}
     * annotation
     *
     * @param userId - user identifier
     * @return list of subscription entities {@link Subscription}
     */
    @Query(FIND_SUB_BY_USER_ID_QUERY)
    List<? extends T> findByUserId(@Param("userId") final Long userId);
}
