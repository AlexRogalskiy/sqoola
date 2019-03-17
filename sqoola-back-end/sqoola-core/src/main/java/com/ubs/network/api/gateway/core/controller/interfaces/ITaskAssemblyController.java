package com.ubs.network.api.rest.core.controller.interfaces;

import com.ubs.network.api.rest.core.model.dto.TaskAssemblyDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskAssemblyEntity;

public interface ITaskAssemblyController<E extends TaskAssemblyEntity, D extends TaskAssemblyDTO> extends ICoreBaseController<E, D> {
}
