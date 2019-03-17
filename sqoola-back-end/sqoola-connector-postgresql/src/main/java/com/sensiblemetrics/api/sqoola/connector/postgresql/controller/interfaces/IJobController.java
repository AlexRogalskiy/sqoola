package com.ubs.network.api.gateway.services.jenkins.controller.interfaces;

import com.ubs.network.api.rest.services.jenkins.model.dto.JobDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.JobEntity;

public interface IJobController<E extends JobEntity, D extends JobDTO> extends IJenkinsBaseController<E, D> {
}
