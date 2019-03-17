package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.connector.interfaces;

import com.ubs.network.api.rest.common.connector.interfaces.IRestBaseConnector;
import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;

public interface IArtifactoryRestBaseConnector<E extends IBaseEntity, D extends IBaseDTO> extends IRestBaseConnector<E, D> {

}
