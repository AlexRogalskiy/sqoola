package com.wildbeeslabs.sensiblemetrics.sqoola.producer.impl;

import com.dinamexoft.carol.triggers.producers.EventProducer;
import com.dinamexoft.carol.triggers.producers.OperationEventProducer;

public abstract class OperationEventProducerImpl<E> implements OperationEventProducer<E> {

    @Override
    public void notify(final E event) {
        getEventProducer().emit(event);
    }

    protected abstract EventProducer<E> getEventProducer();
}
