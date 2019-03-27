package com.sensiblemetrics.api.sqoola.common.producer.iface;

public interface OperationEventProducer<E> {

    void notify(final E operationEvent);
}
