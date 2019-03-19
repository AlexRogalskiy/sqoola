package com.sensiblemetrics.api.sqoola.common.model.dao.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *
 * Base log error DTO model
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseLogErrorEntity extends AbstractBaseErrorEntity {

    private Long id;
    private LocalDateTime logTime;
    private String request;
    private String referer;
    private String qstring;
    private String ipAddress;
    private String agent;
}
