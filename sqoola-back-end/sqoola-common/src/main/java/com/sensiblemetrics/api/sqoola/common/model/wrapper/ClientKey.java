package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


/**
 * Represent a key of the client message received by one mailing
 */
@ApiModel
@Data
@AllArgsConstructor(staticName = "of")
public class ClientKey implements Serializable {

    public static final String DEFAULT_MAILING = "default";
    public static final String DEFAULT_SEGMENT = "default";

    private static final long serialVersionUID = -8415478319140939556L;

    @ApiModelProperty("ID of the client")
    final String clientId;

    @ApiModelProperty("ID of the mailing")
    final String mailingId;

    public String getKey() {
        return clientId + ":" + mailingId;
    }

    public String getKey(String prefix) {
        return prefix + ":" + clientId + ":" + mailingId;
    }
}
