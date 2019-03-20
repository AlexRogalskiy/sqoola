package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SegmentEntry implements Serializable {

    private static final long serialVersionUID = -528926447432813240L;
    private String value;
}
