package com.wildbeeslabs.sensiblemetrics.sqoola.producer;

public interface OperationEventProducer<E> {

    void notify(final E operationEvent);
}
