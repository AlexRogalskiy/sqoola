package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller;

import com.ubs.network.api.rest.services.artifactory.controller.interfaces.ILibraryGroupController;
import com.ubs.network.api.rest.services.artifactory.model.dto.LibraryGroupDTO;
import com.ubs.network.api.gateway.services.artifactory.model.entities.LibraryGroupEntity;

public class LibraryGroupController<E extends LibraryGroupEntity, D extends LibraryGroupDTO> extends ArtifactoryBaseController<E, D> implements ILibraryGroupController<E, D> {
}
