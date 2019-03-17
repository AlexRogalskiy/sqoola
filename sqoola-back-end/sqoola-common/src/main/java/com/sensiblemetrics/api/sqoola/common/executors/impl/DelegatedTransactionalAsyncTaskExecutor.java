package com.sensiblemetrics.api.sqoola.common.executors.impl;

import com.sensiblemetrics.api.sqoola.common.executors.TransactionalAsyncTaskExecutor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Slf4j
public class DelegatedTransactionalAsyncTaskExecutor implements InitializingBean, TransactionalAsyncTaskExecutor {

    private PlatformTransactionManager transactionManager;
    private AsyncTaskExecutor delegate;
    private TransactionTemplate sharedTransactionTemplate;

    public DelegatedTransactionalAsyncTaskExecutor(final PlatformTransactionManager transactionManager, final AsyncTaskExecutor delegate) {
        this.transactionManager = transactionManager;
        this.delegate = delegate;
    }

    @Override
    public void execute(final Runnable task, Integer propagation, final Integer isolationLevel) {
        final TransactionTemplate transactionTemplate = new TransactionTemplate(getTransactionManager());
        transactionTemplate.setPropagationBehavior(propagation);
        transactionTemplate.setIsolationLevel(isolationLevel);
        getDelegate().execute(() -> transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(final TransactionStatus status) {
                task.run();
            }
        }));
    }

    @Override
    public void execute(final Runnable task) {
        execute(task, TransactionDefinition.PROPAGATION_REQUIRED, TransactionDefinition.ISOLATION_DEFAULT);
    }

    @Override
    public void execute(final Runnable task, long startTimeout) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        getDelegate().execute(() ->
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(final TransactionStatus status) {
                    task.run();
                }
            }), startTimeout);
    }

    @Override
    public Future<?> submit(final Runnable task) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        return getDelegate().submit(() -> {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(final TransactionStatus status) {
                    task.run();
                }
            });
        });
    }

    @Override
    public <T> Future<T> submit(final Callable<T> task) {
        final TransactionTemplate transactionTemplate = getSharedTransactionTemplate();
        return getDelegate().submit(() -> transactionTemplate.execute(status -> {
            T result = null;
            try {
                result = task.call();
            } catch (Exception e) {
                log.error(String.format("ERROR: cannot execute callable task = {%s}, message = {%s}", task, e.getMessage()));
                status.setRollbackOnly();
            }
            return result;
        }));
    }

    @Override
    public void afterPropertiesSet() {
        if (Objects.isNull(getTransactionManager())) {
            throw new IllegalArgumentException("ERROR: property 'transactionManager' is not been initialized");
        }
        if (Objects.isNull(getDelegate())) {
            this.delegate = new SimpleAsyncTaskExecutor();
        }
        if (Objects.isNull(getSharedTransactionTemplate())) {
            this.sharedTransactionTemplate = new TransactionTemplate(getTransactionManager());
        }
    }
}
