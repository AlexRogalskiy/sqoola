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
package com.ubs.network.api.gateway.services.jenkins.controller.proxy;

import com.wildbeeslabs.api.rest.common.controller.proxy.ABaseProxyController;
import com.wildbeeslabs.api.rest.common.exception.EmptyContentException;
import com.wildbeeslabs.api.rest.common.model.UserAccountStatusInfo;
import com.wildbeeslabs.api.rest.common.service.interfaces.IHibernateSearchBaseService;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserDTOListWrapper;
import com.wildbeeslabs.api.rest.subscription.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * User Proxy Controller implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 * @param <M>
 */
@Component
public class UserProxyController<T extends User, E extends UserDTO, M extends UserDTOListWrapper<E>> extends ABaseProxyController<T, E, M, Long, IUserService<T>> {

    @Autowired
    private IHibernateSearchBaseService<T> userSearchService;

    /**
     * Get list of user entities {@link User} by subscription status
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} and date (before /
     * after)
     *
     * @param subDate - requested date (optional)
     * @param subStatus - subscription status (optional)
     * @param subDateOrder - date order (true - "after", false - "before" by
     * default) (optional)
     * @return list of user entities {@link User}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntityBySubscriptionStatusAndDate(final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final Optional<Boolean> subDateOrder) throws EmptyContentException {
        getLogger().info("Fetching all users by subscription date {}, status {}, date order {} (1 - before, 0 - after)", subDate, subStatus, subDateOrder);
        IUserService.DateTypeOrder dateTypeOrder = subDateOrder.isPresent() && Objects.equals(Boolean.TRUE, subDateOrder.get()) ? IUserService.DateTypeOrder.AFTER : IUserService.DateTypeOrder.BEFORE;
        List<? extends T> items = getService().findAllBySubscriptionStatusAndDate(subDate, subStatus, dateTypeOrder);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of user entities {@link UserDTO} by subscription status
     * {@link SubscriptionStatusInfo.SubscriptionStatusType} and date (before /
     * after) in container {@link UserDTOListWrapper}
     *
     * @param subDate - requested date (optional)
     * @param subStatus - subscription status (optional)
     * @param subDateOrder - date order (true - "after", false - "before" by
     * default) (optional)
     * @return list of user entities {@link UserDTO} in container
     * {@link UserDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findAllBySubscriptionStatusAndDate(final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final Optional<Boolean> subDateOrder) throws EmptyContentException {
        List<? extends T> items = this.findAllEntityBySubscriptionStatusAndDate(subDate, subStatus, subDateOrder);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get list of user entities {@link User} by subscription ID
     *
     * @param subscriptionId - subscription id
     * @return list of user entities {@link User}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntityBySubscriptionId(final Long subscriptionId) throws EmptyContentException {
        getLogger().info("Fetching all users by subscription id {}", subscriptionId);
        List<? extends T> items = getService().findBySubscriptionId(subscriptionId);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of user entities {@link UserDTO} by subscription ID in container
     * {@link UserDTOListWrapper}
     *
     * @param subscriptionId - subscription id
     * @return list of user entities {@link UserDTO} in container
     * {@link UserDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findAllBySubscriptionId(final Long subscriptionId) throws EmptyContentException {
        List<? extends T> items = this.findAllEntityBySubscriptionId(subscriptionId);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get list of user entities {@link User} by user status
     * {@link User.UserStatusType}
     *
     * @param status - user status
     * @return list of user entities {@link User}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntityByStatus(final UserAccountStatusInfo.StatusType status) throws EmptyContentException {
        getLogger().info("Fetching all users by status {}", status);
        List<? extends T> items = getService().findByStatus(status);
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of user entities {@link User} by subscription status
     * {@link SubscriptionStatusInfo.SubscriptionStatusType}, user status
     * {@link User.UserStatusType} and date (before / after)
     *
     * @param status - user status (optional)
     * @param subDate - requested date (optional)
     * @param subStatus - subscription status (optional)
     * @param subDateOrder - date order (true - "after", false - "before" by
     * default) (optional)
     * @return list of user entities {@link User}
     * @throws EmptyContentException
     */
    public List<? extends T> findAllEntity(final Optional<UserAccountStatusInfo.StatusType> status, final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final Optional<Boolean> subDateOrder) throws EmptyContentException {
        List<? extends T> items = this.findAllEntityBySubscriptionStatusAndDate(subDate, subStatus, subDateOrder);
        if (status.isPresent()) {
            items = items.stream().parallel().filter(item -> Objects.equals(status.get(), item.getStatus())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of user entities {@link UserDTO} by subscription status
     * {@link SubscriptionStatusInfo.SubscriptionStatusType}, user status
     * {@link User.UserStatusType} and date (before / after) in container
     * {@link UserDTOListWrapper}
     *
     * @param status - user status (optional)
     * @param subDate - requested date (optional)
     * @param subStatus - subscription status (optional)
     * @param subDateOrder - date order (true - "after", false - "before" by
     * default) (optional)
     * @return list of user entities {@link UserDTO} in container
     * {@link UserDTOListWrapper}
     * @throws EmptyContentException
     */
    public M findAll(final Optional<UserAccountStatusInfo.StatusType> status, final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final Optional<Boolean> subDateOrder) throws EmptyContentException {
        List<? extends T> items = this.findAllEntity(status, subDate, subStatus, subDateOrder);
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get list of user entities {@link User} by search query parameter combined
     * with array of indexed fields
     *
     * @param searchTerm - requested search term (optional)
     * @param searchFields - array of indexed fields
     * @return list of user entities {@link User}
     * @throws EmptyContentException
     */
    public List<? extends T> searchAllEntity(final Optional<String> searchTerm, final String... searchFields) throws EmptyContentException {
        getLogger().info("Fetching all users by query search term {}", searchTerm);
        List<? extends T> items = (searchTerm.isPresent()) ? userSearchService.fuzzySearch(searchTerm.get(), getEntityClass(), searchFields) : getService().findAll();
        if (CollectionUtils.isEmpty(items)) {
            throw new EmptyContentException(getResource().formatMessage("error.no.content"));
        }
        return items;
    }

    /**
     * Get list of user entities {@link UserDTO} by search query parameter in
     * container {@link UserDTOListWrapper}
     *
     * @param searchTerm - requested search term (optional)
     * @return list of user entities {@link UserDTO} in container
     * {@link UserDTOListWrapper}
     * @throws EmptyContentException
     */
    public M searchAll(final Optional<String> searchTerm) throws EmptyContentException {
        List<? extends T> items = this.searchAllEntity(searchTerm, "firstname, lastname");
        return getDTOConverter().convertToDTOAndWrap(items, getDtoClass(), getDtoListClass());
    }

    /**
     * Get default entities class (@link User)
     *
     * @return entities class instance (@link User)
     */
    @Override
    protected Class<? extends T> getEntityClass() {
        return (Class<? extends T>) User.class;
    }

    /**
     * Get default DTO class (@link UserDTO)
     *
     * @return entities class instance (@link User)
     */
    @Override
    protected Class<? extends E> getDtoClass() {
        return (Class<? extends E>) UserDTO.class;
    }

    /**
     * Get default DTO list wrapper class (@link UserDTOListWrapper)
     *
     * @return DTO list wrapper class instance (@link UserDTOListWrapper)
     */
    @Override
    protected Class<? extends M> getDtoListClass() {
        return (Class<? extends M>) new UserDTOListWrapper<>().getClass();
    }
}
