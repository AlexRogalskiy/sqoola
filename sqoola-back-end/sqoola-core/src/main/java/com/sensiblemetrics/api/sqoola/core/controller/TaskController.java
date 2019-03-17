package com.ubs.network.api.rest.core.controller;

import com.ubs.network.api.rest.core.controller.interfaces.ITaskController;
import com.ubs.network.api.rest.core.model.dto.TaskDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskGroupEntity;

public class TaskController<E extends TaskGroupEntity, D extends TaskDTO> extends CoreBaseController<E, D> implements ITaskController<E, D> {
}
