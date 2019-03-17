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

import com.wildbeeslabs.api.rest.common.service.interfaces.IBaseUserService;
import com.wildbeeslabs.api.rest.common.service.interfaces.IJpaBaseService;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * User REST Application Service declaration {@link User}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
public interface IUserService<T extends User> extends IJpaBaseService<T, Long>, IBaseUserService<T, UserRepository<T>> {

    /**
     * Get list of user entities by subscription status and subscription date
     * (after - excluding / before - including)
     *
     * @param subDate - subscription date
     * @param subStatus - subscription status
     * @param dateTypeOrder - date type order (before / after)
     * @return list of user entities
     */
    List<? extends T> findAllBySubscriptionStatusAndDate(final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final DateTypeOrder dateTypeOrder);

    /**
     * Get list of user entities by subscription date (after - excluding /
     * before - including)
     *
     * @param subDate - subscription date
     * @param dateTypeOrder - date type order (before / after)
     * @return - list of user entities
     */
    List<? extends T> findAllBySubscriptionDate(final Date subDate, final DateTypeOrder dateTypeOrder);

    /**
     * Get list of user entities by subscription date between request period
     * (including)
     *
     * @param startSubDate - subscription start date of period
     * @param endSubDate - subscription end date of period
     * @return - list of user entities
     */
    List<? extends T> findAllBySubscriptionDateBetween(final Date startSubDate, final Date endSubDate);

    /**
     * Get list of user entities by subscription status
     *
     * @param subStatus - subscription type
     * @return list of user entities
     */
    List<? extends T> findAllBySubscriptionStatus(final SubscriptionStatusInfo.StatusType subStatus);

    /**
     * Get list of user entities by subscription ID
     *
     * @param subscriptionId - subscription identifier
     * @return list of user entities
     */
    List<? extends T> findBySubscriptionId(final Long subscriptionId);
}
