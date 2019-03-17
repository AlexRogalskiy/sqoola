package com.ubs.network.api.rest.core.controller.interfaces;

import com.ubs.network.api.rest.core.model.dto.TaskDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskGroupEntity;

public interface ITaskController<E extends TaskGroupEntity, D extends TaskDTO> extends ICoreBaseController<E, D> {
}
