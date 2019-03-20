package com.sensiblemetrics.api.sqoola.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class TaskExecutorConfig extends AsyncConfigurerSupport {

    /**
     * Default task executor thread prefix
     */
    public static final String DEFAULT_TASK_EXECUTOR_THREAD_NAME_PREFIX = "Triggers";
    /**
     * Default task executor thread group name
     */
    public static final String DEFAULT_TASK_EXECUTOR_THREAD_GROUP_NAME = "Trigger group";
    /**
     * Default task executor pool size
     */
    public static final int DEFAULT_EXECUTOR_POOL_SIZE = 10;
    /**
     * Default task executor max pool size
     */
    public static final int DEFAULT_EXECUTOR_MAX_POOL_SIZE = 20;
    /**
     * Default task executor queue size
     */
    public static final int DEFAULT_EXECUTOR_QUEUE_SIZE = 100 * 1000;

    /**
     * Returns asynchronous task executor instance {@link ThreadPoolTaskExecutor}
     *
     * @return asynchronous task executor {@link ThreadPoolTaskExecutor}
     */
    @Bean
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix(DEFAULT_TASK_EXECUTOR_THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.setThreadGroupName(DEFAULT_TASK_EXECUTOR_THREAD_GROUP_NAME);
        threadPoolTaskExecutor.setCorePoolSize(DEFAULT_EXECUTOR_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(DEFAULT_EXECUTOR_MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(DEFAULT_EXECUTOR_QUEUE_SIZE);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //threadPoolTaskExecutor.setTaskDecorator(new MdcTaskDecorator());
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }
}
