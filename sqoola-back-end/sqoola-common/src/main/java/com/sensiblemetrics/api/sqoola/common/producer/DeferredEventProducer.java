package com.sensiblemetrics.api.sqoola.common.producer;

public interface DeferredEventProducer<E, T> extends EventProducer<E>, TransactionObserver<T> {
}
