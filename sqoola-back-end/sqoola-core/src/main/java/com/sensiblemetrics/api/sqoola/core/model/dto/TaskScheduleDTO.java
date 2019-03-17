package com.ubs.network.api.rest.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ubs.network.api.rest.common.model.dto.BaseScheduleDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskScheduleDTO extends BaseScheduleDTO {
}
