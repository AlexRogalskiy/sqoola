package com.ubs.network.api.gateway.services.jenkins.controller.interfaces;

import com.ubs.network.api.rest.services.jenkins.model.dto.BuildDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.BuildEntity;

public interface IBuildController<E extends BuildEntity, D extends BuildDTO> extends IJenkinsBaseController<E, D> {
}
