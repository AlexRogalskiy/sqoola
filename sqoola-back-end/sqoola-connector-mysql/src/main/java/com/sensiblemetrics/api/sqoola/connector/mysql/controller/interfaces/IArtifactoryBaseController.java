package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller.interfaces;

import com.ubs.network.api.rest.common.controller.interfaces.IBaseController;
import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;

public interface IArtifactoryBaseController<E extends IBaseEntity, D extends IBaseDTO> extends IBaseController<E, D> {
}
