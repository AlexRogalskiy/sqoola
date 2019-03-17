package com.sensiblemetrics.api.sqoola.common.producer.impl;

import com.sensiblemetrics.api.sqoola.common.producer.DeferredEventProducer;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Supports events broadcasting only after the transaction has been completed (to ensure the originating class
 * to be persisted to the database prior to receiving the events)
 */
public class DeferredEventProducerImpl<E, T> implements DeferredEventProducer<E, T> {

//    @Produce
//    private ProducerTemplate producerTemplate;

    private String uri;

    private ThreadLocal<List<E>> deferredEvents = new ThreadLocal<>();

    public DeferredEventProducerImpl(final String uri) {
        this.uri = uri;
    }

    @Override
    public void emit(final E event) {
        List<E> deferredEvents = this.deferredEvents.get();
        if (Objects.isNull(deferredEvents)) {
            deferredEvents = new LinkedList<>();
            this.deferredEvents.set(deferredEvents);
        }
        deferredEvents.add(event);
    }

    @Override
    public void onComplete(final T transaction) {
        final List<E> deferredEvents = this.deferredEvents.get();
        this.deferredEvents.remove();
        if (Objects.nonNull(deferredEvents)) {
            for (final E event : deferredEvents) {
                //this.producerTemplate.sendBody(this.uri, event);
            }
        }
    }
}
