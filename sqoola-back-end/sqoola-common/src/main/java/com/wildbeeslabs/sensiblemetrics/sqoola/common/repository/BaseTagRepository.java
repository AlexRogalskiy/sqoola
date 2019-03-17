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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.repository;

import com.wildbeeslabs.api.rest.common.model.BaseTagEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * BaseTag REST Application storage repository to manage {@link BaseTagEntity}
 * instances
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@NoRepositoryBean
public interface BaseTagRepository<T extends BaseTagEntity> extends BaseRepository<T> {

    /**
     * Get tag entities {@link BaseTagEntity} by tag name
     *
     * @param name - tag name
     * @return tag entities {@link BaseTagEntity} in optional container
     * {@link Optional}
     */
    Optional<? extends T> findByName(final String name);

    /**
     * Get list of tag entities {@link BaseTagEntity} by tag name pattern
     *
     * @param namePattern - tag name pattern
     * @return list of tag entities {@link BaseTagEntity}
     */
    List<? extends T> findByNameLike(final String namePattern);
}
