package com.ubs.network.api.rest.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskGroupDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskGroupEntity;
import com.ubs.network.api.gateway.core.repository.ITaskGroupRepository;

public class TaskGroupRepository<E extends TaskGroupEntity, D extends TaskGroupDTO> extends CoreBaseRepository<E, D> implements ITaskGroupRepository<E, D> {

}
