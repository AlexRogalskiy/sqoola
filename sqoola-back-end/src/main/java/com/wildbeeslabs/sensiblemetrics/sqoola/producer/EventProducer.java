package com.wildbeeslabs.sensiblemetrics.sqoola.producer;

public interface EventProducer<E> {

    void emit(final E event);
}
