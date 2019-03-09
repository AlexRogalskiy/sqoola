package com.wildbeeslabs.sensiblemetrics.sqoola.common.executors.impl;

import lombok.*;
import org.hibernate.Session;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.util.Objects;

public class DelegatedHibernateJpaDialect extends HibernateJpaDialect {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -134519664226435728L;

    @Override
    public Object beginTransaction(final EntityManager entityManager, final TransactionDefinition definition) throws PersistenceException, TransactionException {
        final Session session = (Session) entityManager.getDelegate();
        if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
            getSession(entityManager).getTransaction().setTimeout(definition.getTimeout());
        }

        final TransactionData data = new TransactionData();
        session.doWork(connection -> {
            final Integer previousIsolationLevel = DataSourceUtils.prepareConnectionForTransaction(connection, definition);
            data.setPreviousIsolationLevel(previousIsolationLevel);
            data.setConnection(connection);
        });

        entityManager.getTransaction().begin();

        final Object springTransactionData = prepareTransaction(entityManager, definition.isReadOnly(), definition.getName());
        data.setSpringTransactionData(springTransactionData);
        return data;
    }

    @Override
    public void cleanupTransaction(Object transactionData) {
        super.cleanupTransaction(((TransactionData) transactionData).getSpringTransactionData());
        ((TransactionData) transactionData).resetIsolationLevel();
    }

    @Data
    @EqualsAndHashCode
    @ToString
    private static class TransactionData {
        private Object springTransactionData;
        @Getter(AccessLevel.PRIVATE)
        private Integer previousIsolationLevel;
        @Getter(AccessLevel.PRIVATE)
        private Connection connection;

        public void resetIsolationLevel() {
            if (Objects.nonNull(this.previousIsolationLevel)) {
                DataSourceUtils.resetConnectionAfterTransaction(connection, previousIsolationLevel);
            }
        }
    }
}
