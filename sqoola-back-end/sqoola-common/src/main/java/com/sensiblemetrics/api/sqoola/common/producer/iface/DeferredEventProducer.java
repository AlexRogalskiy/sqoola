package com.sensiblemetrics.api.sqoola.common.producer.iface;

public interface DeferredEventProducer<E, T> extends EventProducer<E>, TransactionObserver<T> {
}
