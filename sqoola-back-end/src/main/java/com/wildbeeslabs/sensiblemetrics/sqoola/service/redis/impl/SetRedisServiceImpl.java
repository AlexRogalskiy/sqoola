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
package com.wildbeeslabs.sensiblemetrics.sqoola.service.redis.impl;

import com.wildbeeslabs.sensiblemetrics.sqoola.model.redis.CacheItem;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * {@link Set} redis service implementation
 */
@Service(SetRedisService.SERVICE_ID)
public class SetRedisServiceImpl extends BaseRedisServiceImpl<CacheItem<?>> implements SetRedisService<CacheItem<?>> {

    @Resource(name = "redisTemplate")
    private SetOperations<String, CacheItem<?>> itemSetOperations;

    @Override
    public void add(final String key, final CacheItem<?>... items) {
        this.itemSetOperations.add(key, items);
    }

    @Override
    public Set<CacheItem<?>> getAll(final String key) {
        return this.itemSetOperations.members(key);
    }

    @Override
    public long count(final String key) {
        return this.itemSetOperations.size(key);
    }

    @Override
    public long remove(final String key, final CacheItem<?>... items) {
        return this.itemSetOperations.remove(key, items);
    }
} 
