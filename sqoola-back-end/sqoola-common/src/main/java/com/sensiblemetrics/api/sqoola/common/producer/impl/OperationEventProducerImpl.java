package com.sensiblemetrics.api.sqoola.common.producer.impl;

import com.sensiblemetrics.api.sqoola.common.producer.iface.EventProducer;
import com.sensiblemetrics.api.sqoola.common.producer.iface.OperationEventProducer;

public abstract class OperationEventProducerImpl<E> implements OperationEventProducer<E> {

    @Override
    public void notify(final E event) {
        getEventProducer().emit(event);
    }

    protected abstract EventProducer<E> getEventProducer();
}
