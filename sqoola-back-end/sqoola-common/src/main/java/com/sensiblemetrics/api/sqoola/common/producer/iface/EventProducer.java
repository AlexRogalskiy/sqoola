package com.sensiblemetrics.api.sqoola.common.producer.iface;

public interface EventProducer<E> {

    void emit(final E event);
}
