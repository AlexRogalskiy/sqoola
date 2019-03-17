package com.ubs.network.api.gateway.services.jenkins.controller;

import com.ubs.network.api.rest.services.jenkins.model.dto.BuildDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.BuildEntity;

public class BuildController<E extends BuildEntity, D extends BuildDTO> extends JenkinsBaseController<E, D> implements IBuildController<E, D> {
}
