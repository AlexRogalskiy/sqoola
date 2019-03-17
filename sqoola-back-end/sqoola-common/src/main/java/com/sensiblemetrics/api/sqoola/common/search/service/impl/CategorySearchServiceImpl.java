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
package com.sensiblemetrics.api.sqoola.common.search.service.impl;

import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.Category;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.document.interfaces.SearchableCategory;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.repository.CategorySearchRepository;
import com.wildbeeslabs.sensiblemetrics.supersolr.search.service.CategorySearchService;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * Category {@link CategorySearchService} implementation
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Service(CategorySearchService.SERVICE_ID)
@Transactional
public class CategorySearchServiceImpl extends BaseDocumentSearchServiceImpl<Category, String> implements CategorySearchService {

    @Autowired
    private CategorySearchRepository categorySearchRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByTitleLike(final String title, final Pageable pageable) {
        return getRepository().findByTitleLike(title, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByTitle(final String title, final Pageable pageable) {
        return getRepository().findByTitle(title, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByTitles(final String titles, final Pageable pageable) {
        if (StringUtils.isEmpty(titles)) {
            return getRepository().findAll(pageable);
        }
        return getRepository().findByTitleIn(tokenize(titles), pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByDescription(final String description, final Pageable pageable) {
        return getRepository().findByDescription(description, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public HighlightPage<? extends Category> findByTitleIn(final Collection<String> values, final Pageable pageable) {
        if (CollectionUtils.isEmpty(values)) {
            return new SolrResultPage<>(Collections.emptyList());
        }
        return getRepository().findByTitleIn(values, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public FacetPage<? extends Category> findByAutoCompleteTitleFragment(final String fragment, final Pageable pageable) {
        if (StringUtils.isEmpty(fragment)) {
            return new SolrResultPage<>(Collections.emptyList());
        }
        return getRepository().findByTitleStartingWith(fragment, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public HighlightPage<? extends Category> find(final String collection, final String searchTerm, final Pageable page) {
        final Criteria fileIdCriteria = new Criteria(SearchableCategory.ID_FIELD_NAME).boost(2).is(searchTerm);
        final Criteria titleCriteria = new Criteria(SearchableCategory.TITLE_FIELD_NAME).fuzzy(searchTerm);
        final SimpleHighlightQuery query = new SimpleHighlightQuery(fileIdCriteria.or(titleCriteria), page);
        query.setHighlightOptions(new HighlightOptions()
            .setSimplePrefix("<highlight>")
            .setSimplePostfix("</highlight>")
            .addField(SearchableCategory.ID_FIELD_NAME, SearchableCategory.TITLE_FIELD_NAME));
        return getSolrTemplate().queryForHighlightPage(collection, query, Category.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByQuery(final String collection, final Query query) {
        return this.findByQuery(collection, query, Category.class);
    }

    @Override
    @Transactional(readOnly = true)
    public FacetPage<? extends Category> findByFacetQuery(final String collection, final FacetQuery facetQuery) {
        return this.findByFacetQuery(collection, facetQuery, Category.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByCriteria(final String collection, final Criteria criteria, final Pageable pageable) {
        return this.findByCriteria(collection, criteria, pageable, Category.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<? extends Category> findByQueryAndCriteria(final String collection, final String queryString, final Criteria criteria, final Pageable pageable) {
        return this.findByQueryAndCriteria(collection, queryString, criteria, pageable, Category.class);
    }

    protected Criteria nameOrDescSearchCriteria(final String searchTerm) {
        final String[] searchTerms = StringUtils.split(searchTerm, DEFAULT_SEARСH_TERM_DELIMITER);
        Criteria criteria = new Criteria();
        for (final String term : searchTerms) {
            criteria = criteria
                .and(new Criteria(SearchableCategory.TITLE_FIELD_NAME).contains(term))
                .or(new Criteria(SearchableCategory.DESCRIPTION_FIELD_NAME).contains(term));
        }
        return criteria.and(new Criteria(DEFAULT_DOCTYPE).is(SearchableCategory.CORE_ID));
    }

    /**
     * Returns {@link CategorySearchRepository} repository
     *
     * @return {@link CategorySearchRepository} repository
     */
    @Override
    protected CategorySearchRepository getRepository() {
        return this.categorySearchRepository;
    }
}
