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
import com.wildbeeslabs.api.rest.common.model.UserAccountStatusInfo;
import com.wildbeeslabs.api.rest.subscription.controller.interfaces.IUserController;
import com.wildbeeslabs.api.rest.subscription.controller.proxy.UserProxyController;
import com.wildbeeslabs.api.rest.subscription.model.User;
import com.wildbeeslabs.api.rest.subscription.model.SubscriptionStatusInfo;
import com.wildbeeslabs.api.rest.subscription.model.dto.UserDTO;
import com.wildbeeslabs.api.rest.subscription.model.dto.wrapper.UserDTOListWrapper;

import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * User REST Controller implementation
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
public class UserController<T extends User, E extends UserDTO, M extends UserDTOListWrapper<E>> extends ABaseController<T, E, Long, UserProxyController<T, E, M>> implements IUserController<T, E, M> {

    @InitBinder
    public void initBinder(final WebDataBinder dataBinder) {
        final BaseEnumConverter<SubscriptionStatusInfo.StatusType> converter = new BaseEnumConverter<>(SubscriptionStatusInfo.StatusType.class);
        dataBinder.registerCustomEditor(SubscriptionStatusInfo.StatusType.class, converter);
    }

    /**
     * Get list of user entities by subscription type / date / date order
     *
     * @param status - user status
     * @param subStatus - subscription status
     * @param subDate - subscription date
     * @param subDateOrder - date order (before / after)
     * @return list of user entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> getAll(@RequestParam(name = "status", required = false) Optional<UserAccountStatusInfo.StatusType> status, @RequestParam(name = "subdate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd") Optional<Date> subDate, @RequestParam(name = "substatus", required = false) Optional<SubscriptionStatusInfo.StatusType> subStatus, @RequestParam(name = "order", required = false, defaultValue = "false") Optional<Boolean> subDateOrder) {
        try {
            return new ResponseEntity<>(getProxyController().findAll(status, subDate, subStatus, subDateOrder), HttpStatus.OK);
        } catch (EmptyContentException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get user entities by ID
     *
     * @param id - user identifier
     * @return user entities in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{id:[\\d]+}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    /**
     * Create new user entities
     *
     * @param userDto - user data transfer object
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> create(@RequestBody @Valid E userDto) {
        /*
        UriComponentsBuilder bc = UriComponentsBuilder.newInstance();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(request.getRequestURI() + "/{id}").buildAndExpand(userDto.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
         */
        return super.create(userDto);
    }

    /**
     * Update user entities
     *
     * @param id - user identifier
     * @param userDto - user data transfer object
     * @return updated user entities in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{id:[\\d]+}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid E userDto) {
        return super.update(id, userDto);
    }

    /**
     * Delete user entities
     *
     * @param id - user identifier
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/user/{id:[\\d]+}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    /**
     * Delete all user entities
     *
     * @return response status code in response container {@link ResponseEntity}
     */
    @RequestMapping(value = "/users", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<?> deleteAll() {
        return super.deleteAll();
    }

    /**
     * Get list of user entities by search parameter (optional)
     *
     * @param searchTerm - search term (optional)
     * @return list of user entities in response container
     * {@link ResponseEntity}
     */
    @RequestMapping(value = "/users/search", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    public ResponseEntity<?> search(@RequestParam(name = "search", required = false) Optional<String> searchTerm) {
        try {
            return new ResponseEntity<>(getProxyController().searchAll(searchTerm), HttpStatus.OK);
        } catch (EmptyContentException ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
