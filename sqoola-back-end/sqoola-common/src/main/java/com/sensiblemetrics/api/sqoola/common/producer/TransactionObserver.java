package com.sensiblemetrics.api.sqoola.common.producer;

public interface TransactionObserver<T> {

    void onComplete(final T transaction);
}
