package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer;

public interface EventProducer<E> {

    void emit(final E event);
}
