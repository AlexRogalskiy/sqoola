package com.sensiblemetrics.api.sqoola.common.service.impl;

import com.dinamexoft.carol.triggers.services.BaseQueryService;
import com.sensiblemetrics.api.sqoola.common.service.iface.BaseQueryService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class BaseQueryServiceImpl<E extends Serializable> implements BaseQueryService<E> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<E> executeQuery(final String queryString) {
        Query query = null;
        try {
            query = getEntityManager().createNamedQuery(queryString);
            query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);
            query.setHint(QueryHints.CACHE_MODE, CacheMode.GET);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> executeQuery(final String queryString, final Class<? extends E> queryMapping) {
        Query query = null;
        try {
            query = getEntityManager().createNamedQuery(queryString, queryMapping);
            query.setHint(QueryHints.CACHEABLE, Boolean.TRUE);
            query.setHint(QueryHints.CACHE_MODE, CacheMode.GET);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
