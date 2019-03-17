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
import com.wildbeeslabs.api.rest.common.exception.ResourceNotFoundException;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserSubOrderDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserSubOrderDTOListWrapper;
import com.wildbeeslabs.api.rest.subscription.service.interfaces.IUserSubOrderService;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 *
 * UserSubscription Proxy Controller implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 * @param <M>
 */
@Component
public class UserSubscriptionProxyController<T extends UserSubOrder, E extends UserSubOrderDTO, M extends UserSubOrderDTOListWrapper<E>> extends ABaseProxyController<T, E, M, UserSubOrder.UserSubOrderId, IUserSubOrderService<T>> {

    /**
     * Get subscription order entities {@link UserSubOrderDTO} by user
     * {@link User} and subscription {@link Subscription}
     *
     * @param userItem - user entities
     * @param subscriptionItem - subscription entities
     * @return subscription order entities {@link UserSubOrderDTO}
     */
    public E findByUserAndSubscription(final User userItem, final Subscription subscriptionItem) {
        final T item = this.findAllEntityByUserAndSubscription(userItem, subscriptionItem);
        return getDTOConverter().convertToDTO(item, getDtoClass());
    }

    /**
     * Get subscription order entities {@link UserSubOrder} by user {@link User}
     * and subscription {@link Subscription}
     *
     * @param userItem - user entities
     * @param subscriptionItem - subscription entities
     * @return subscription order entities {@link UserSubOrder}
     */
    public T findAllEntityByUserAndSubscription(final User userItem, final Subscription subscriptionItem) {
        getLogger().info("Fetching subscription order by user id {} and subscription id {}", userItem.getId(), subscriptionItem.getId());
        final Optional<? extends T> currentOrder = getService().findByUserAndSubscription(userItem, subscriptionItem);
        if (!currentOrder.isPresent()) {
            throw new ResourceNotFoundException(getResource().formatMessage("error.no.order.item.user.subscription.id", userItem.getId(), subscriptionItem.getId()));
        }
        return currentOrder.get();
    }

    /**
     * Get list of subscription order entities {@link UserSubOrder} by user
     * {@link User}
     *
     * @param userItem - user entities
     * @return list of subscription order entities {@link UserSubOrder}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntityByUser(final User userItem) throws EmptyContentException {
        getLogger().info("Fetching subscription orders by user id {}", userItem.getId());
        List<? extends T> items = getService().findByUser(userItem);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of subscription order entities {@link UserSubOrderDTO} by user
     * {@link User} in container {@link UserSubOrderDTOListWrapper}
     *
     * @param userItem - user entities
     * @return list of subscription order entities {@link UserSubOrderDTO} in
     * container {@link UserSubOrderDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findByUser(final User userItem) throws EmptyContentException {
        List<? extends T> items = this.findAllEntityByUser(userItem);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get list of subscription order entities {@link UserSubOrder} by
     * subscription {@link Subscription}
     *
     * @param subscriptionItem - subscription entities
     * @return list of subscription order entities {@link UserSubOrder}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntityBySubscription(final Subscription subscriptionItem) throws EmptyContentException {
        getLogger().info("Fetching subscription orders by subscription id {}", subscriptionItem.getId());
        List<? extends T> items = getService().findBySubscription(subscriptionItem);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of subscription order entities {@link UserSubOrderDTO} by
     * subscription {@link Subscription} in container
     * {@link UserSubOrderDTOListWrapper}
     *
     * @param subscriptionItem - subscription entities
     * @return list of subscription order entities {@link UserSubOrderDTO} in
     * container {@link UserSubOrderDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findBySubscription(final Subscription subscriptionItem) throws EmptyContentException {
        List<? extends T> items = this.findAllEntityBySubscription(subscriptionItem);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get updated subscription order entities {@link UserSubOrderDTO}
     *
     * @param toItemEntity - subscription order entities to be updated
     * @param fromItemDto - subscription order entities to get updates from
     * @return updated subscription order entities {@link UserSubOrderDTO}
     */
    public E updateEntityItem(final T toItemEntity, final E fromItemDto) {
        getLogger().info("Updating item by id {}", toItemEntity.getId());
        final T fromItemEntity = getDTOConverter().convertToEntity(fromItemDto, getEntityClass());
        getService().merge(toItemEntity, fromItemEntity);
        return getDTOConverter().convertToDTO(toItemEntity, getDtoClass());
    }

    /**
     * Get deleted subscription order entities {@link UserSubOrderDTO}
     *
     * @param itemEntity - subscription order entities to be deleted
     * @return deleted subscription order entities {@link UserSubOrderDTO}
     */
    public E deleteEntityItem(final T itemEntity) {
        getLogger().info("Deleting item by id {}", itemEntity.getId());
        getService().delete(itemEntity);
        return getDTOConverter().convertToDTO(itemEntity, getDtoClass());
    }

    /**
     * Delete list of subscription order entities {@link UserSubOrder}
     *
     * @param itemEntityList - list of subscription order entities to be deleted
     */
    public void deleteEntityItems(final List<? extends T> itemEntityList) {
        getLogger().info("Deleting items {}", StringUtils.join(itemEntityList, ", "));
        getService().delete(itemEntityList);
    }

    /**
     * Get default entities class (@link UserSubOrder)
     *
     * @return entities class instance (@link UserSubOrder)
     */
    @Override
    protected Class<? extends T> getEntityClass() {
        return (Class<? extends T>) UserSubOrder.class;
    }

    /**
     * Get default DTO class (@link UserSubOrderDTO)
     *
     * @return entities class instance (@link UserSubOrder)
     */
    @Override
    protected Class<? extends E> getDtoClass() {
        return (Class<? extends E>) UserSubOrderDTO.class;
    }

    /**
     * Get default DTO list wrapper class (@link UserSubOrderDTOListWrapper)
     *
     * @return DTO list wrapper class instance (@link
     * UserSubOrderDTOListWrapper)
     */
    @Override
    protected Class<? extends M> getDtoListClass() {
        return (Class<? extends M>) new UserSubOrderDTOListWrapper<>().getClass();
    }
}
