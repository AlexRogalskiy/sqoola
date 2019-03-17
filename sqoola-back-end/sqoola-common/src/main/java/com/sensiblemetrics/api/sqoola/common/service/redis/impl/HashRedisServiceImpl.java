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
package com.sensiblemetrics.api.sqoola.common.service.redis.impl;

import com.sensiblemetrics.api.sqoola.common.model.redis.HashCacheItem;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;

/**
 * {@link Map} redis service implementation
 */
@Service(HashRedisService.SERVICE_ID)
public class HashRedisServiceImpl<ID extends Serializable> extends BaseRedisServiceImpl<HashCacheItem<?, ID>> implements HashRedisService<HashCacheItem<?, ID>, ID> {

    @Resource(name = "hashRedisTemplate")
    private HashOperations<String, ID, HashCacheItem<?, ID>> itemHashOperations;

    @Override
    public void add(final String key, final HashCacheItem<?, ID> item) {
        this.itemHashOperations.putIfAbsent(key, item.getId(), item);
    }

    @Override
    public void update(final String key, final HashCacheItem<?, ID> item) {
        this.itemHashOperations.put(key, item.getId(), item);
    }

    @Override
    public HashCacheItem<?, ID> get(final String key, final ID id) {
        return this.itemHashOperations.get(key, id);
    }

    @Override
    public long count(final String key) {
        return this.itemHashOperations.size(key);
    }

    @Override
    public Map<ID, HashCacheItem<?, ID>> getAll(final String key) {
        return this.itemHashOperations.entries(key);
    }

    @Override
    public long remove(final String key, final ID... ids) {
        return this.itemHashOperations.delete(key, ids);
    }
} 
