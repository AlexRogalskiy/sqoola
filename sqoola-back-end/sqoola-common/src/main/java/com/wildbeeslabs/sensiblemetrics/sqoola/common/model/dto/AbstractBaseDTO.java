package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dto;

import com.ubs.network.api.rest.common.model.interfaces.IBaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractBaseDTO implements IBaseDTO {
}
