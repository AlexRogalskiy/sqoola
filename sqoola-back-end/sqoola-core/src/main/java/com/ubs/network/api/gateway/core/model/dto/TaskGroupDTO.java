package com.ubs.network.api.rest.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ubs.network.api.rest.common.model.dto.BaseGroupUnitDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskGroupDTO extends BaseGroupUnitDTO {
    private List<TaskDTO> tasks;
}
