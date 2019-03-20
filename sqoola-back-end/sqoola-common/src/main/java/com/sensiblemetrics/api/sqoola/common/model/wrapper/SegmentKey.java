package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SegmentKey implements Serializable {

    private static final long serialVersionUID = -8593253079283956680L;

    private String segment;
    private String mailing;

}
