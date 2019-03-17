package com.ubs.network.api.rest.core.repository;

import com.ubs.network.api.rest.core.model.dto.TaskScheduleDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskScheduleEntity;
import com.ubs.network.api.gateway.core.repository.ITaskScheduleRepository;

public class TaskScheduleRepository<E extends TaskScheduleEntity, D extends TaskScheduleDTO> extends CoreBaseRepository<E, D> implements ITaskScheduleRepository<E, D> {
}
