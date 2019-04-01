package com.sensiblemetrics.api.sqoola.common.model.iface;

import java.io.Serializable;

/**
 * Stateful interface declaration
 *
 * @param <T> type of state entity
 */
public interface Stateful<T extends Serializable> extends Serializable {

    T getState();

    void setState(final T state);
}
