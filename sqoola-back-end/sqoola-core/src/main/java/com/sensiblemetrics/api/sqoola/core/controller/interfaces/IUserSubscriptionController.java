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
package com.ubs.network.api.gateway.core.controller.interfaces;

import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserSubOrderDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserSubOrderDTOListWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * UserSubscription REST Controller declaration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 * @param <M>
 */
public interface IUserSubscriptionController<T extends UserSubOrder, E extends UserSubOrderDTO, M extends UserSubOrderDTOListWrapper<E>> {

    ResponseEntity<?> getSubscriptionsByUserId(final Long userId);

    ResponseEntity<?> getSubscriptionByUserIdAndSubscriptionId(final Long userId, final Long subscriptionId);

    ResponseEntity<?> createSubscriptionByUserId(final Long userId, final E orderDto, final UriComponentsBuilder ucBuilder);

    ResponseEntity<?> updateSubscriptionsByUserIdAndSubscriptionId(final Long userId, final Long subscriptionId, final E orderDto);

    ResponseEntity<?> deleteSubscriptionsByUserId(final Long userId, final Long subscriptionId);

    ResponseEntity<?> deleteAllSubscriptions(final Long userId);

    ResponseEntity<?> getUsersBySubscriptionId(final Long subscriptionId);
}
