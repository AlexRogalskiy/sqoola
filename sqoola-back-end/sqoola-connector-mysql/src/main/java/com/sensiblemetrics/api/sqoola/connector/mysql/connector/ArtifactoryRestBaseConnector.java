package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.connector;

import com.ubs.network.api.rest.common.connector.ARestBaseConnector;
import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;
import com.ubs.network.api.rest.services.artifactory.connector.interfaces.IArtifactoryRestBaseConnector;

public class ArtifactoryRestBaseConnector<E extends IBaseEntity, D extends IBaseDTO> extends ARestBaseConnector<E, D> implements IArtifactoryRestBaseConnector<E, D> {

}
