package com.ubs.network.api.gateway.services.jenkins.controller;

import com.ubs.network.api.rest.services.jenkins.model.dto.ProfileDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.ProfileEntity;

public class ProfileController<E extends ProfileEntity, D extends ProfileDTO> extends JenkinsBaseController<E, D> implements IProfileController<E, D> {
}
