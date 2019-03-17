package com.sensiblemetrics.api.sqoola.common.service.dao;

import com.wildbeeslabs.api.rest.common.model.BaseCategoryEntity;
import com.wildbeeslabs.api.rest.common.repository.BaseCategoryRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * BaseCategory REST Application Service declaration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 * @param <T>
 * @param <R>
 */
public interface CategoryDaoService<E extends BaseCategoryEntity, ID extends Serializable> extends IBaseEntityService<E, ID> {

    /**
     * Get category entities {@link BaseCategoryEntity} by name
     *
     * @param name - category name
     * @return category entities {@link BaseCategoryEntity} in optional container
     * {@link Optional}
     */
    default Optional<? extends T> findByName(final String name) {
        return getRepository().findByName(name);
    }

    /**
     * Get list of category entities {@link BaseCategoryEntity} by parent entities
     * category {@link BaseCategoryEntity}
     *
     * @param parentId - parent category entities
     * @return list of category entities {@link BaseCategoryEntity}
     */
    default List<? extends T> findByParentId(final BaseCategoryEntity parentId) {
        return getRepository().findByParentId(parentId);
    }
}
