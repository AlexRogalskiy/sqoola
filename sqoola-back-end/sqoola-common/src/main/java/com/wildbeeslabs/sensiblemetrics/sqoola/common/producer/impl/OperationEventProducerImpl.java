package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.impl;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.EventProducer;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.OperationEventProducer;

public abstract class OperationEventProducerImpl<E> implements OperationEventProducer<E> {

    @Override
    public void notify(final E event) {
        getEventProducer().emit(event);
    }

    protected abstract EventProducer<E> getEventProducer();
}
