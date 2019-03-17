package com.ubs.network.api.rest.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ubs.network.api.rest.common.model.dto.BaseUnitDTO;
import com.ubs.network.api.rest.common.model.interfaces.IManageableUnit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDTO extends BaseUnitDTO {
}
