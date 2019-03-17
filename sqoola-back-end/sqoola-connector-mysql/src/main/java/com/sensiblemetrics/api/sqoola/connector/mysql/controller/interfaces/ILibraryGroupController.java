package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller.interfaces;

import com.ubs.network.api.rest.services.artifactory.model.dto.LibraryGroupDTO;
import com.ubs.network.api.gateway.services.artifactory.model.entities.LibraryGroupEntity;

public interface ILibraryGroupController<E extends LibraryGroupEntity, D extends LibraryGroupDTO> extends IArtifactoryBaseController<E, D> {
}
