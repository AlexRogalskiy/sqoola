package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.handler.DefaultRejectedExecutionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfig {

    /**
     * Default thread executor pool size
     */
    public static final int DEFAULT_EXECUTOR_POOL_SIZE = 5;
    /**
     * Default thread executor max pool size
     */
    public static final int DEFAULT_EXECUTOR_MAX_POOL_SIZE = 10;
    /**
     * Default thread executor keep alive timeout
     */
    public static final int DEFAULT_EXECUTOR_ALIVE_TIMEOUT = 0;
    /**
     * Default thread executor queue size
     */
    public static final int DEFAULT_EXECUTOR_QUEUE_SIZE = 8 * 1024;

    /**
     * Returns thread pool executor instance {@link ThreadPoolExecutor}
     *
     * @return thread pool executor {@link ThreadPoolExecutor}
     */
    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            DEFAULT_EXECUTOR_POOL_SIZE,
            DEFAULT_EXECUTOR_MAX_POOL_SIZE,
            DEFAULT_EXECUTOR_ALIVE_TIMEOUT,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(DEFAULT_EXECUTOR_QUEUE_SIZE));
        executor.setRejectedExecutionHandler(new DefaultRejectedExecutionHandler());
        return executor;
    }
}
