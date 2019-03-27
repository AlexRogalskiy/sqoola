package com.sensiblemetrics.api.sqoola.common.producer.iface;

public interface TransactionObserver<T> {

    void onComplete(final T transaction);
}
