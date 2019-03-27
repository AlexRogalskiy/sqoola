/*
 * The MIT License
 *
 * Copyright 2017 Pivotal Software, Inc..
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
package com.sensiblemetrics.api.sqoola.common.service.impl;

import com.sensiblemetrics.api.sqoola.common.service.iface.IHibernateSearchBaseService;
import com.wildbeeslabs.api.rest.common.model.IBaseEntity;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.lucene.search.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wildbeeslabs.api.rest.common.service.interfaces.IHibernateSearchBaseService;

/**
 *
 * Common Hibernate Search REST Application Service implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 */
@Service("commonBaseHibernateSearchService")
@Transactional
public class HibernateSearchBaseServiceImpl<T extends IBaseEntity> implements IHibernateSearchBaseService<T> {

    /**
     * Default Logger instance
     */
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    protected final EntityManager entityManager;

    @Autowired
    public HibernateSearchBaseServiceImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void initializeHibernateSearch() {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            LOGGER.error("ERROR: cannot initialize hibernate search service", ex);
        }
    }

    @Transactional
    @Override
    public List<? extends T> fuzzySearch(final String searchTerm, final Class<? extends T> clazz, final String... searchFields) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields(searchFields).matching(searchTerm).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, clazz);

        List<T> resultList = Collections.EMPTY_LIST;
        try {
            resultList = jpaQuery.getResultList();
        } catch (NoResultException ex) {
            LOGGER.error("ERROR: cannot get fuzzy search resultSet", ex);
        }
        return resultList;
    }
}
