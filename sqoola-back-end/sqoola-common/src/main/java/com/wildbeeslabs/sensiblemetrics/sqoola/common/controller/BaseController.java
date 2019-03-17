/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * Base search controller declaration
 *
 * @author Alex
 * @version 1.0.0
 */
public interface BaseController {

    /**
     * Default not allowed {@link ResponseEntity}
     */
    ResponseEntity<?> DEFAULT_NOT_ALLOWED_RESPONSE = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

    /**
     * Returns {@link ResponseEntity} body with all fetched items
     *
     * @return response body {@link ResponseEntity}
     */
    default ResponseEntity<?> getAll() {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }

    /**
     * Returns {@link ResponseEntity} body with item fetched by ID
     *
     * @param id - initial input entity identifier
     * @return response body {@link ResponseEntity}
     */
    default <ID extends Serializable> ResponseEntity<?> getById(final ID id) {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }

    /**
     * Returns {@link ResponseEntity} body with newly created item
     *
     * @param itemDto - initial input entity dto
     * @return response body {@link ResponseEntity}
     */
    default <T> ResponseEntity<?> create(final T itemDto) {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }

    /**
     * Returns {@link ResponseEntity} body with updated item
     *
     * @param id      - initial input entity identifier
     * @param itemDto - initial input entity dto
     * @return response body {@link ResponseEntity}
     */
    default <T, ID extends Serializable> ResponseEntity<?> update(final ID id, final T itemDto) {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }

    /**
     * Returns {@link ResponseEntity} body with deleted item
     *
     * @param id - initial input entity identifier
     * @return response body {@link ResponseEntity}
     */
    default <ID extends Serializable> ResponseEntity<?> delete(final ID id) {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }

    /**
     * Returns {@link ResponseEntity} with empty body
     *
     * @return response body {@link ResponseEntity}
     */
    default ResponseEntity<?> deleteAll() {
        return DEFAULT_NOT_ALLOWED_RESPONSE;
    }
}
