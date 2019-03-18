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
package com.sensiblemetrics.api.sqoola.common.search.repository;

import com.sensiblemetrics.api.sqoola.common.search.document.BaseDocument;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Query;

import java.io.Serializable;
import java.util.Optional;

/**
 * Custom base document search repository declaration {@link AuditDocumentSearchRepository}
 *
 * @param <E>  type of base document {@link BaseDocument}
 * @param <ID> type of base document identifier {@link Serializable}
 */
@NoRepositoryBean
public interface BaseDocumentSearchRepository<E extends BaseDocument<ID>, ID extends Serializable> extends AuditDocumentSearchRepository<E, ID> {

    @RestResource(rel = "by-id", description = @Description(value = "find documents by id"))
    @Query(name = "BaseDocument.findById")
    Optional<E> findById(@Boost(2) final ID id);
}
