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

import com.wildbeeslabs.api.rest.common.model.BaseDictionaryInfoEntity;
import com.wildbeeslabs.api.rest.common.model.BaseInfoEntity;
import com.wildbeeslabs.api.rest.common.service.interfaces.IBaseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * BaseInfo REST Application storage repository to manage {@link BaseInfoEntity}
 * instances
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@NoRepositoryBean
public interface BaseInfoRepository<T extends BaseInfoEntity> extends IBaseRepository<T> {

    /**
     * Get information entities {@link BaseInfoEntity} by prefix name
     *
     * @param prefix - information prefix name
     * @return information entities {@link BaseInfoEntity} in optional container
     * {@link Optional}
     */
    Optional<? extends T> findByPrefix(final String prefix);

    /**
     * Get list of information entities {@link BaseInfoEntity} by dictionary
     * entities {@link BaseDictionaryInfoEntity}
     *
     * @param dictionary - dictionary entities
     * @return list of information entities {@link BaseInfoEntity}
     */
    List<? extends T> findByDictionary(final BaseDictionaryInfoEntity dictionary);
}
