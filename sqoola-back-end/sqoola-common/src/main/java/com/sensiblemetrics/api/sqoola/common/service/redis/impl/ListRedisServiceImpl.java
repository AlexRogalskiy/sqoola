/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common.service.redis.impl;

import com.sensiblemetrics.api.sqoola.common.model.redis.CacheItem;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@link List} redis service implementation
 */
@Service(ListRedisService.SERVICE_ID)
public class ListRedisServiceImpl extends BaseRedisServiceImpl<CacheItem<?>> implements ListRedisService<CacheItem<?>> {

    @Resource(name = "redisTemplate")
    private ListOperations<String, CacheItem<?>> itemListOperations;

    @Override
    public void addBefore(final String key, final CacheItem<?> item) {
        this.itemListOperations.leftPush(key, item);
    }

    @Override
    public void addAfter(final String key, final CacheItem<?> item) {
        this.itemListOperations.rightPush(key, item);
    }

    @Override
    public long count(final String key) {
        return this.itemListOperations.size(key);
    }

    @Override
    public CacheItem<?> getAt(final String key, final long index) {
        return this.itemListOperations.index(key, index);
    }

    @Override
    public void remove(final String key, final CacheItem<?> item) {
        this.itemListOperations.remove(key, 1, item);
    }
} 
