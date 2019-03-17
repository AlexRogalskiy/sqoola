package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dto.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class BaseLogErrorDTO extends AbstractBaseErrorDTO {

    private Long id;
    private LocalDateTime logTime;
    private String request;
    private String referer;
    private String qstring;
    private String ipAddress;
    private String agent;
}
