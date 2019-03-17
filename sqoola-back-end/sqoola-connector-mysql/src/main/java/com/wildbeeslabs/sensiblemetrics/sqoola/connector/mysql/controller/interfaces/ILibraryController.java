package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller.interfaces;

import com.ubs.network.api.rest.services.artifactory.model.dto.LibraryDTO;
import com.ubs.network.api.gateway.services.artifactory.model.entities.LibraryEntity;

public interface ILibraryController<E extends LibraryEntity, D extends LibraryDTO> extends IArtifactoryBaseController<E, D> {
}
