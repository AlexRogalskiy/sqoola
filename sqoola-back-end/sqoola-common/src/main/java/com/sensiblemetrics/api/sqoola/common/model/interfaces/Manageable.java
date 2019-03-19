package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;

/**
 * Manageable entity interface declaration
 *
 * @param <T> type of manageable status
 */
public interface Manageable<T extends Serializable> extends Serializable {

    void invoke();

    T getStatus();

    void receive(final String data);

    String send();
}
