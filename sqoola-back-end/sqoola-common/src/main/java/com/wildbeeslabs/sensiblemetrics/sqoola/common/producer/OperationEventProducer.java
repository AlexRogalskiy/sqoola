package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer;

public interface OperationEventProducer<E> {

    void notify(final E operationEvent);
}
