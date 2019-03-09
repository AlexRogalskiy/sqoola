package com.wildbeeslabs.sensiblemetrics.sqoola.producer;

public interface TransactionObserver<T> {

    void onComplete(final T transaction);
}
