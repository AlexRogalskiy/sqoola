package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Segment implements Serializable {
    private static final long serialVersionUID = 6811997223373851998L;
    private SegmentKey key;
    private List<SegmentEntry> entries;
}
