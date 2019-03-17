package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller;

import com.ubs.network.api.rest.common.controller.ABaseController;
import com.ubs.network.api.rest.services.artifactory.controller.interfaces.IArtifactoryBaseController;
import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.ubs.network.api.rest.common.model.interfaces.IBaseEntity;

public class ArtifactoryBaseController<E extends IBaseEntity, D extends IBaseDTO> extends ABaseController<E, D> implements IArtifactoryBaseController<E, D> {

}
