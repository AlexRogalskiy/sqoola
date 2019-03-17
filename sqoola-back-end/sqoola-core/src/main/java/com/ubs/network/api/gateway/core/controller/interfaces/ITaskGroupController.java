package com.ubs.network.api.rest.core.controller.interfaces;

import com.ubs.network.api.rest.core.model.dto.TaskGroupDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskEntity;

public interface ITaskGroupController<E extends TaskEntity, D extends TaskGroupDTO> extends ICoreBaseController<E, D> {
}
