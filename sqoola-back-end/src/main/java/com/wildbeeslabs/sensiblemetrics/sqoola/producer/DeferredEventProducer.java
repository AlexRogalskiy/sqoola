package com.wildbeeslabs.sensiblemetrics.sqoola.producer;

public interface DeferredEventProducer<E, T> extends EventProducer<E>, TransactionObserver<T> {
}
