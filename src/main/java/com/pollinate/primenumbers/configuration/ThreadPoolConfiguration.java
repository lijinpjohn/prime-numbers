package com.pollinate.primenumbers.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * This configuration class enable thread pool
 *
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolConfiguration.class);

    @Value("${prime.number.corePoolSize}")
    private Integer corePoolSize;
    @Value("${prime.number.maxPoolSize}")
    private Integer maxPoolSize;
    @Value("${prime.number.queueCapacity}")
    private Integer queueCapacity;

    /**
     * Here we set all the required parameter for the thread pool.
     * All these properties can be configured in application.properties.
     * @return
     */
    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("Prime Thread-");
        executor.initialize();
        return executor;
    }

}
