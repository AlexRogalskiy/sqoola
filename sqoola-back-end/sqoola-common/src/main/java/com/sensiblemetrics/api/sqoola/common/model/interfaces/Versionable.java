package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;

/**
 * Versionable interface declaration
 *
 * @param <T> type of version entity
 */
public interface Versionable<T extends Serializable> extends Serializable {

    T getVersion();

    void setVersion(final T version);
}
