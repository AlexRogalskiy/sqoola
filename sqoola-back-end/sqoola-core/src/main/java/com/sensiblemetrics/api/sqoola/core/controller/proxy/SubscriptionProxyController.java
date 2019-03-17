/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
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
package com.ubs.network.api.gateway.core.controller.proxy;

import com.wildbeeslabs.api.rest.common.controller.proxy.ABaseProxyController;
import com.wildbeeslabs.api.rest.common.exception.EmptyContentException;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.dto.SubscriptionDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.SubscriptionDTOListWrapper;
import com.wildbeeslabs.api.rest.subscription.service.interfaces.ISubscriptionService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 *
 * Subscription Proxy Controller implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 * @param <M>
 */
@Component
public class SubscriptionProxyController<T extends Subscription, E extends SubscriptionDTO, M extends SubscriptionDTOListWrapper<E>> extends ABaseProxyController<T, E, M, Long, ISubscriptionService<T>> {

    /**
     * Get list of subscription order entities {@link SubscriptionDTO} by user
     * ID in container {@link SubscriptionDTOListWrapper}
     *
     * @param userId - user id
     * @return list of subscription order entities {@link SubscriptionDTO} in
     * container {@link SubscriptionDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findByUserId(final Long userId) throws EmptyContentException {
        getLogger().info("Fetching all subscriptions by user id {}", userId);
        List<? extends T> items = getService().findByUserId(userId);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get list of subscription order entities {@link SubscriptionDTO} by
     * subscription status {@link SubscriptionStatusInfo.SubscriptionStatusType}
     * in container {@link SubscriptionDTOListWrapper}
     *
     * @param subStatus - subscription status (optional)
     * @return list of subscription order entities {@link SubscriptionDTO} in
     * container {@link SubscriptionDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findAll(final Optional<SubscriptionStatusInfo.StatusType> subStatus) throws EmptyContentException {
        getLogger().info("Fetching all subscriptions by status {}", subStatus);
        List<? extends T> items = (!subStatus.isPresent()) ? getService().findAll() : getService().findByStatus(subStatus.get());
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get default entities class (@link Subscription)
     *
     * @return entities class instance (@link Subscription)
     */
    @Override
    protected Class<? extends T> getEntityClass() {
        return (Class<? extends T>) Subscription.class;
    }

    /**
     * Get default DTO class (@link SubscriptionDTO)
     *
     * @return entities class instance (@link SubscriptionDTO)
     */
    @Override
    protected Class<? extends E> getDtoClass() {
        return (Class<? extends E>) SubscriptionDTO.class;
    }

    /**
     * Get default DTO list wrapper class (@link SubscriptionDTOListWrapper)
     *
     * @return DTO list wrapper class instance (@link
     * SubscriptionDTOListWrapper)
     */
    @Override
    protected Class<? extends M> getDtoListClass() {
        return (Class<? extends M>) new SubscriptionDTOListWrapper<>().getClass();
    }
}
