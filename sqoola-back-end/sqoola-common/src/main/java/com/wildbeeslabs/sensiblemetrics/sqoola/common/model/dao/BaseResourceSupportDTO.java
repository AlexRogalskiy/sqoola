package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResourceSupportDTO extends BaseResourceSupport {

}
