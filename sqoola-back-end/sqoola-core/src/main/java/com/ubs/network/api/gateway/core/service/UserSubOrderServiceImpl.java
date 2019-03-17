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
package com.ubs.network.api.gateway.core.service;

import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.subscription.repository.UserSubOrderRepository;
import com.wildbeeslabs.api.rest.subscription.service.interfaces.IUserSubOrderService;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * UserSubOrder REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Service("userSubOrderService")
@Transactional
public class UserSubOrderServiceImpl<T extends UserSubOrder> extends JpaBaseServiceImpl<T, UserSubOrder.UserSubOrderId, UserSubOrderRepository<T>> implements IUserSubOrderService<T> {

    @Override
    public boolean isExist(final T userSubOrder) {
        return Objects.nonNull(findById(userSubOrder.getId()));
    }

    @Override
    public List<? extends T> findByUser(final User user) {
        return getRepository().findByUser(user);
    }

    @Override
    public List<? extends T> findBySubscription(final Subscription subscription) {
        return getRepository().findBySubscription(subscription);
    }

    @Override
    public List<? extends T> findByStartedAtBetween(final Date dateFrom, final Date dateTo) {
        return getRepository().findByStartedAtBetween(dateFrom, dateTo);
    }

    @Override
    public Optional<? extends T> findByUserAndSubscription(final User user, final Subscription subscription) {
        final UserSubOrder order = new UserSubOrder();
        order.setSubscription(subscription);
        order.setUser(user);
        return this.findById(order.getId());
    }
}
