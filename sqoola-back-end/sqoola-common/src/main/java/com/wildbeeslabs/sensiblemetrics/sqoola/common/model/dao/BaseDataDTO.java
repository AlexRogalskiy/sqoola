package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import com.ubs.network.api.rest.common.model.dto.AbstractBaseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseDataDTO extends AbstractBaseDTO {
    private Long id;
    private String title;
    private String description;
    private String code;
}
