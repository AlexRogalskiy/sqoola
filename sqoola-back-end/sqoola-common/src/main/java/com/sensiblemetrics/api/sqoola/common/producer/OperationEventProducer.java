package com.sensiblemetrics.api.sqoola.common.producer;

public interface OperationEventProducer<E> {

    void notify(final E operationEvent);
}
