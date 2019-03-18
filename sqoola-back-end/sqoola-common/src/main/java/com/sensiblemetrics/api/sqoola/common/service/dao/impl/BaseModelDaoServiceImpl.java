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
package com.sensiblemetrics.api.sqoola.common.service.dao.impl;

import com.sensiblemetrics.api.sqoola.common.model.dao.BaseModelEntity;
import com.sensiblemetrics.api.sqoola.common.repository.BaseModelRepository;
import com.sensiblemetrics.api.sqoola.common.service.dao.BaseModelDaoService;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;

/**
 * {@link BaseModelEntity} service implementation
 *
 * @param <E>  type of base model {@link BaseModelEntity}
 * @param <ID> type of base model identifier {@link Serializable}
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Transactional
public abstract class BaseModelDaoServiceImpl<E extends BaseModelEntity<ID>, ID extends Serializable> extends AuditModelDaoServiceImpl<E, ID> implements BaseModelDaoService<E, ID> {

    @Override
    public void saveOrUpdate(final E target, final Class<? extends E> clazz) {
        log.info("Saving or updating target entity: {}", target);
        if (target.isNew()) {
            getEntityManager().persist(target);
        } else {
            final E targetEntity = getEntityManager().find(clazz, target.getId());
            if (Objects.isNull(targetEntity)) {
                getEntityManager().persist(target);
            } else {
                getEntityManager().merge(target);
            }
        }
    }

    protected abstract BaseModelRepository<E, ID> getRepository();
}
