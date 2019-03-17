package com.sensiblemetrics.api.sqoola.common.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseLogErrorDTO implements Serializable {
    private Long id;
    private LocalDateTime logtime;
    private String request;
    private String referer;
    private String qstring;
    private String ipAddress;
    private String agent;
    private String errorMessage;
    private String errorCode;
}
