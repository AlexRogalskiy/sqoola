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
package com.sensiblemetrics.api.sqoola.common.utility;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Custom query utilities implementation
 */
@Slf4j
@UtilityClass
public class QueryUtils {

    public Properties load(final DataSource dataSource) {
        final Properties properties = new Properties();
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> configs = jdbcTemplate.queryForList("select config_key, config_value from config_params");
        configs.forEach((config) -> {
            properties.setProperty(String.valueOf(config.get("config_key")), String.valueOf(config.get("config_value")));
        });
        return properties;
    }

    @SuppressWarnings("unchecked")
    public static List<String[]> executeNativeQueryWithCastCheck(final String statement, final EntityManager em) {
        final Query query = em.createNativeQuery(statement);
        final List<?> results = query.getResultList();

        if (results.isEmpty()) {
            return Collections.emptyList();
        }

        if (results.get(0) instanceof String) {
            return ((List<String>) results).stream().map(s -> new String[]{s}).collect(Collectors.toList());
        } else {
            return (List<String[]>) results;
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> E executeSingleNativeQuery(final String queryString, final String mapping, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString, mapping);
            return (E) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNativeQuery(final String queryString, final String mapping, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString, mapping);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> E executeSingleNativeQuery(final String queryString, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString);
            return (E) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> E executeSingleNativeQuery(final String queryString, final Class<? extends E> clazz, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString, clazz);
            return (E) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNativeQuery(final String queryString, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNativeQuery(final String queryString, final Class<? extends E> clazz, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNativeQuery(queryString, clazz);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> E executeSingleNamedQuery(final String queryString, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNamedQuery(queryString);
            return (E) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> E executeSingleNamedQuery(final String queryString, final Class<? extends E> clazz, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNamedQuery(queryString, clazz);
            return (E) query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNamedQuery(final String queryString, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNamedQuery(queryString);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNamedQuery(final String queryString, final Map<String, Object> params, final EntityManager em) {
        try {
            final Query query = em.createNamedQuery(queryString);
            Optional.of(params).orElse(Collections.emptyMap()).entrySet().forEach((Map.Entry<String, Object> entry) -> query.setParameter(entry.getKey(), entry.getValue()));
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query, message: {}", e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNamedQuery(final String queryString, final Class<? extends E> queryMapping, final EntityManager em) {
        Query query = null;
        try {
            query = em.createNamedQuery(queryString, queryMapping);
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query: {}, message: {}", query, e.getMessage());
        }
        return Collections.emptyList();
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> executeMultipleNamedQuery(final String queryString, final Class<? extends E> queryMapping, final Map<String, Object> params, final EntityManager em) {
        try {
            final Query query = em.createNamedQuery(queryString, queryMapping);
            Optional.of(params).orElse(Collections.emptyMap()).entrySet().forEach((Map.Entry<String, Object> entry) -> query.setParameter(entry.getKey(), entry.getValue()));
            return (List<E>) query.getResultList();
        } catch (Exception e) {
            log.error("ERROR: cannot execute query, message: {}", e.getMessage());
        }
        return Collections.emptyList();
    }
}
