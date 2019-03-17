package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.interfaces;

public interface Manageable {
    void invoke();

    String getStatus();

    void receive(final String data);

    String send();
}
