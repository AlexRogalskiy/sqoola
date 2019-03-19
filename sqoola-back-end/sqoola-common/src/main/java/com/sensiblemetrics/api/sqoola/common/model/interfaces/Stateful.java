package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;

/**
 * Stateful entity interface declaration
 *
 * @param <T> type of state entity
 */
public interface Stateful<T extends Serializable> extends Serializable {

    T getState();

    void setState(final T state);
}
