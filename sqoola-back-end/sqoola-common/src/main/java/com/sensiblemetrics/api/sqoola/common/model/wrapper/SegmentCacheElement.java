package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class SegmentCacheElement implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 7677929909118573919L;

    private long createdAt;
    private List<SegmentEntry> entries;
}
