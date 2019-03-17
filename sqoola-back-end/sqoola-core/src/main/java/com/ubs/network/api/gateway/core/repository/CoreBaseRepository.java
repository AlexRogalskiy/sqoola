package com.ubs.network.api.rest.core.repository;

import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;
import com.ubs.network.api.rest.common.repository.ABaseCrudRepository;
import com.ubs.network.api.gateway.core.repository.ICoreBaseRepository;

public class CoreBaseRepository<E extends IBaseEntity, D extends IBaseDTO> extends ABaseCrudRepository<E, D> implements ICoreBaseRepository<E, D> {
}
