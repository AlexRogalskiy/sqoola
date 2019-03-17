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

import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.repository.UserRepository;
import com.wildbeeslabs.api.rest.subscription.service.interfaces.IUserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * User REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Service("userService")
@Transactional
public class UserServiceImpl<T extends User> extends JpaBaseServiceImpl<T, Long, UserRepository<T>> implements IUserService<T> {

    @Override
    public Optional<? extends T> findByLogin(final String name) {
        return getRepository().findByLoginIgnoreCase(name);
    }

    @Override
    public boolean isExist(final T user) {
        return findByLogin(user.getLogin()).isPresent();
    }

    @Override
    public List<? extends T> findAllBySubscriptionStatusAndDate(final Optional<Date> subDate, final Optional<SubscriptionStatusInfo.StatusType> subStatus, final IUserService.DateTypeOrder dateTypeOrder) {
//        if (subDate.isPresent()) {
//            return getRepository().findByOptionalSubscriptionStatusAndOptionalDate(subDate, subStatus, dateTypeOrder.name());
//        } else if (subStatus.isPresent()) {
//            return this.findAllBySubscriptionStatus(subStatus.get());
//        }
//        return this.findAll();
        return getRepository().findByOptionalSubscriptionStatusAndOptionalDate(subDate, subStatus, dateTypeOrder.name());
    }

    @Override
    public List<? extends T> findAllBySubscriptionDateBetween(final Date startSubDate, final Date endSubDate) {
        return getRepository().findBySubscriptionDateBetween(startSubDate, endSubDate);
    }

    @Override
    public List<? extends T> findAllBySubscriptionDate(final Date subDate, final IUserService.DateTypeOrder dateTypeOrder) {
        return getRepository().findBySubscriptionDate(subDate, dateTypeOrder.name());
    }

    @Override
    public List<? extends T> findAllBySubscriptionStatus(final SubscriptionStatusInfo.StatusType subStatus) {
        return getRepository().findBySubscriptionStatus(subStatus);
    }

    @Override
    public List<? extends T> findBySubscriptionId(final Long subscriptionId) {
        return getRepository().findBySubscriptionId(subscriptionId);
    }
}
