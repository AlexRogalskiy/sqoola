package com.sensiblemetrics.api.sqoola.common.service.iface;

import java.io.Serializable;
import java.util.Optional;

/**
 * BaseCategory REST Application Service declaration
 *
 * @param <E>
 * @param <ID>
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public interface BaseCategoryDaoService<E extends BaseCategory<ID>, ID extends Serializable> extends BaseModelDaoService<E, ID> {

    /**
     * Get category entities {@link BaseCategory} by name
     *
     * @param name - category name
     * @return category entities {@link BaseCategory} in optional container
     * {@link Optional}
     */
    default Optional<? extends E> findByName(final String name) {
        return getRepository().findByName(name);
    }

    /**
     * Get list of category entities {@link BaseCategory} by parent entities
     * category {@link BaseCategory}
     *
     * @param parentId - parent category entities
     * @return list of category entities {@link BaseCategory}
     */
    default Iterable<? extends E> findByParentId(final BaseCategory parentId) {
        return getRepository().findByParentId(parentId);
    }
}
