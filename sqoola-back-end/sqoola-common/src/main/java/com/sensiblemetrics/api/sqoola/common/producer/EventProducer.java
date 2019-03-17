package com.sensiblemetrics.api.sqoola.common.producer;

public interface EventProducer<E> {

    void emit(final E event);
}
