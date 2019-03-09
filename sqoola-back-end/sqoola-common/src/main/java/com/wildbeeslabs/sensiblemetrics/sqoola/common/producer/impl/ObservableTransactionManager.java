package com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.impl;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.producer.TransactionObserver;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ObservableTransactionManager<T> extends JpaTransactionManager {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2497130288181897365L;

    private final List<TransactionObserver<T>> observers;

    public ObservableTransactionManager(final EntityManagerFactory emf, final List<TransactionObserver<T>> observers) {
        super(emf);
        this.observers = observers;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doCleanupAfterCompletion(final Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        for (final TransactionObserver<T> observer : this.observers) {
            observer.onComplete((T) transaction);
        }
    }
}
