package com.ubs.network.api.rest.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskEntity;
import com.ubs.network.api.gateway.core.repository.ITaskRepository;

public class TaskRepository<E extends TaskEntity, D extends TaskDTO> extends CoreBaseRepository<E, D> implements ITaskRepository<E, D> {
}
