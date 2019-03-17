package com.ubs.network.api.rest.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskAssemblyDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskAssemblyEntity;
import com.ubs.network.api.gateway.core.repository.ITaskAssemblyGroupRepository;

public class TaskAssemblyGroupRepository<E extends TaskAssemblyEntity, D extends TaskAssemblyDTO> extends CoreBaseRepository<E, D> implements ITaskAssemblyGroupRepository<E, D> {
}
