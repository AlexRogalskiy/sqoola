package com.ubs.network.api.rest.core.controller;

import com.ubs.network.api.rest.core.controller.interfaces.ITaskAssemblyController;
import com.ubs.network.api.rest.core.model.dto.TaskAssemblyDTO;
import com.ubs.network.api.gateway.core.model.entities.TaskAssemblyEntity;

public class TaskAssemblyController<E extends TaskAssemblyEntity, D extends TaskAssemblyDTO> extends CoreBaseController<E, D> implements ITaskAssemblyController<E, D> {
}
