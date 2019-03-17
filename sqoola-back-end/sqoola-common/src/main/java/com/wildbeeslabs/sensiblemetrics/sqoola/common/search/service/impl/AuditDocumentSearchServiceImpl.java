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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.search.service.impl;

import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.AuditDocument;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.repository.AuditDocumentSearchRepository;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.service.AuditDocumentSearchService;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Audit {@link AuditDocumentSearchService} implementation
 *
 * @param <E>  type of audit document {@link AuditDocument}
 * @param <ID> type of audit document identifier {@link Serializable}
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Transactional
public abstract class AuditDocumentSearchServiceImpl<E extends AuditDocument, ID extends Serializable> extends BaseSearchServiceImpl<E, ID> implements AuditDocumentSearchService<E, ID> {

    @Autowired
    private SolrTemplate solrTemplate;

    protected SolrTemplate getSolrTemplate() {
        return this.solrTemplate;
    }

    /**
     * Returns {@link AuditDocumentSearchRepository} repository
     *
     * @return {@link AuditDocumentSearchRepository} repository
     */
    protected abstract AuditDocumentSearchRepository<E, ID> getRepository();
}
