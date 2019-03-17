package com.sensiblemetrics.api.sqoola.common.executors;

import org.springframework.core.task.AsyncTaskExecutor;

public interface TransactionalAsyncTaskExecutor extends AsyncTaskExecutor {

    void execute(final Runnable task, final Integer propagation, final Integer isolationLevel);
}
