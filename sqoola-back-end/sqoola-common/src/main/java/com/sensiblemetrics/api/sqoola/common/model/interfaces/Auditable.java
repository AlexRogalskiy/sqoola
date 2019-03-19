package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;
import java.time.temporal.Temporal;

/**
 * Auditable interface declaration
 *
 * @param <T> type of temporal entity
 */
public interface Auditable<T extends Temporal> extends Serializable {

    T getCreated();

    void setCreated(final T created);

    String getCreatedBy();

    void setCreatedBy(final String createdBy);

    T getChanged();

    void setChanged(final T changed);

    String getChangedBy();

    void setChangedBy(final String changedBy);
}
