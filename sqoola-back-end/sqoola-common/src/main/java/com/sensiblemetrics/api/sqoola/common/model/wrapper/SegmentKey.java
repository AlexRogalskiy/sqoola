package com.sensiblemetrics.api.sqoola.common.model.wrapper;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class SegmentKey implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -5899150520964785297L;

    @NonNull
    @Getter
    private Long id;
}
