package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;
import java.time.temporal.Temporal;

/**
 * Auditable interface declaration
 *
 * @param <T> type of temporal entity
 * @param <T> type of principal entity
 */
public interface Auditable<T extends Temporal, S extends Serializable> extends Serializable {

    T getCreated();

    void setCreated(final T created);

    S getCreatedBy();

    void setCreatedBy(final S createdBy);

    T getChanged();

    void setChanged(final T changed);

    S getChangedBy();

    void setChangedBy(final S changedBy);
}
