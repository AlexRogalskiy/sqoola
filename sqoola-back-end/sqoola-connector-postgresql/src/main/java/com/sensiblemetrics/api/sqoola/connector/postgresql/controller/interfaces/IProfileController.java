package com.ubs.network.api.gateway.services.jenkins.controller.interfaces;

import com.ubs.network.api.rest.services.jenkins.model.dto.ProfileDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.ProfileEntity;

public interface IProfileController<E extends ProfileEntity, D extends ProfileDTO> extends IJenkinsBaseController<E, D> {
}
