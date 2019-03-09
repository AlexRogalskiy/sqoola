package com.wildbeeslabs.sensiblemetrics.sqoola.common.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Default rejected execution handler
 */
@Slf4j
public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {

    public void rejectedExecution(final Runnable runnable, final ThreadPoolExecutor executor) {
        try {
            executor.getQueue().put(runnable);
        } catch (InterruptedException e) {
            log.error("ERROR: cannot handle queued task: {}, message: {}", runnable, e.getMessage());
            throw new RejectedExecutionException(e);
        }
    }
}
