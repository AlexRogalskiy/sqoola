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
package com.ubs.network.api.gateway.core.controller;

import com.wildbeeslabs.api.rest.common.controller.ABaseController;
import com.wildbeeslabs.api.rest.common.exception.EmptyContentException;
import com.wildbeeslabs.api.rest.subscription.controller.interfaces.IUserSubscriptionController;
import com.wildbeeslabs.api.rest.subscription.controller.proxy.SubscriptionProxyController;
import com.wildbeeslabs.api.rest.subscription.controller.proxy.UserProxyController;
import com.wildbeeslabs.api.rest.subscription.controller.proxy.UserSubscriptionProxyController;
import com.wildbeeslabs.api.rest.subscription.model.Subscription;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.UserSubOrder;
import com.wildbeeslabs.api.rest.subscription.model.dto.SubscriptionDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserSubOrderDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.SubscriptionDTOListWrapper;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserDTOListWrapper;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserSubOrderDTOListWrapper;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * User-Subscription REST Controller implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <E>
 * @param <M>
 */
@RestController
@RequestMapping("/api/data")
public class UserSubscriptionController<T extends UserSubOrder, E extends UserSubOrderDTO, M extends UserSubOrderDTOListWrapper<E>> extends ABaseController<T, E, UserSubOrder.UserSubOrderId, UserSubscriptionProxyController<T, E, M>> implements IUserSubscriptionController<T, E, M> {

    @Autowired
    private SubscriptionProxyController<Subscription, SubscriptionDTO, SubscriptionDTOListWrapper<SubscriptionDTO>> subscriptionProxyController;
    @Autowired
    private UserProxyController<User, UserDTO, UserDTOListWrapper<UserDTO>> userProxyController;

    /**
     * Get list of subscription entities by user ID
     *
     * @param userId - user identifier
     * @return list of subscription entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscriptions", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> getSubscriptionsByUserId(@PathVariable("userId") Long userId) {
//        User userItem = userProxyController.getEntityItemById(userId);
//        try {
//            return new ResponseEntity<>(userSubscriptionProxyController.findByUser(userItem), HttpStatus.OK);
//        } catch (EmptyContentException ex) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        try {
            return new ResponseEntity<>(subscriptionProxyController.findByUserId(userId), HttpStatus.OK);
        } catch (EmptyContentException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get subscription order entities by user ID / subscription ID
     *
     * @param userId - user identifier
     * @param subscriptionId - subscription identifier
     * @return subscription order entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscription/{subscriptionId:[\\d]+}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> getSubscriptionByUserIdAndSubscriptionId(@PathVariable("userId") Long userId, @PathVariable("subscriptionId") Long subscriptionId) {
        User userItem = userProxyController.getEntityItemById(userId);
        Subscription subscriptionItem = subscriptionProxyController.getEntityItemById(subscriptionId);
        return new ResponseEntity<>(getProxyController().findByUserAndSubscription(userItem, subscriptionItem), HttpStatus.OK);
    }

    /**
     * Create new subscription order entities by user ID
     *
     * @param userId - user identifier
     * @param orderDto - subscription order data transfer object
     * @param ucBuilder - URI component builder
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscription", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus
    @Override
    public ResponseEntity<?> createSubscriptionByUserId(@PathVariable("userId") Long userId, @RequestBody @Valid E orderDto, UriComponentsBuilder ucBuilder) {
        UserDTO userItem = userProxyController.getItemById(userId);
        SubscriptionDTO subscriptionItem = subscriptionProxyController.getItemById(orderDto.getSubscription().getId());
        orderDto.setSubscription(subscriptionItem);
        orderDto.setUser(userItem);
        getProxyController().createItem(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update subscription order entities by user ID / subscription ID
     *
     * @param userId - user identifier
     * @param subscriptionId - subscription identifier
     * @param orderDto - subscription order data transfer object
     * @return updated subscription order entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscription/{subscriptionId:[\\d]+}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> updateSubscriptionsByUserIdAndSubscriptionId(@PathVariable("userId") Long userId, @PathVariable("subscriptionId") Long subscriptionId, @RequestBody @Valid E orderDto) {
        User userItem = userProxyController.getEntityItemById(userId);
        Subscription subscriptionItem = subscriptionProxyController.getEntityItemById(orderDto.getSubscription().getId());
        T currentOrder = getProxyController().findAllEntityByUserAndSubscription(userItem, subscriptionItem);
        getProxyController().updateEntityItem(currentOrder, orderDto);
        return new ResponseEntity<>(currentOrder, HttpStatus.OK);
    }

    /**
     * Delete subscription order entities by user ID / subscription ID
     *
     * @param userId - user identifier
     * @param subscriptionId - subscription identifier
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscription/{subscriptionId:[\\d]+}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus
    @Override
    public ResponseEntity<?> deleteSubscriptionsByUserId(@PathVariable("userId") Long userId, @PathVariable("subscriptionId") Long subscriptionId) {
        User userItem = userProxyController.getEntityItemById(userId);
        Subscription subscriptionItem = subscriptionProxyController.getEntityItemById(subscriptionId);
        T currentOrder = getProxyController().findAllEntityByUserAndSubscription(userItem, subscriptionItem);
        getProxyController().deleteEntityItem(currentOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete all subscription order entities by user ID
     *
     * @param userId - user identifier
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{userId:[\\d]+}/subscriptions", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus
    @Override
    public ResponseEntity<?> deleteAllSubscriptions(@PathVariable("userId") Long userId) {
        User userItem = userProxyController.getEntityItemById(userId);
        try {
            List<? extends T> subscriptionOrders = getProxyController().findAllEntityByUser(userItem);
            getProxyController().deleteEntityItems(subscriptionOrders);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyContentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    /**
     * Get list of user entities by subscription ID
     *
     * @param subscriptionId - subscription identifier
     * @return list of user entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/subscription/{subscriptionId:[\\d]+}/users", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> getUsersBySubscriptionId(@PathVariable("subscriptionId") Long subscriptionId) {
//        Subscription subscriptionItem = subscriptionProxyController.getEntityItemById(subscriptionId);
//        try {
//            return new ResponseEntity<>(userSubscriptionProxyController.findBySubscription(subscriptionItem), HttpStatus.OK);
//        } catch (EmptyContentException ex) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        try {
            return new ResponseEntity<>(userProxyController.findAllEntityBySubscriptionId(subscriptionId), HttpStatus.OK);
        } catch (EmptyContentException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
