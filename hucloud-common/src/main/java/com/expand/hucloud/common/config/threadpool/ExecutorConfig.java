package com.expand.hucloud.common.config.threadpool;

import com.expand.hucloud.common.constant.AppConstant;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author hdq
 * @time 2023/12/30 23:43
 */

@Configuration
@EnableAsync
@Slf4j
public class ExecutorConfig {



    @Bean(AppConstant.DEFAULT_TASK)
    public Executor defaultExecutor() {
        return this.createExecutor(AppConstant.DEFAULT_TASK);
    }


    private Executor createExecutor(String taskName) {
        //coreSize:核心线程数;maxSize:最大线程数;queueCapacity:队列大小;awaitSeconds:线程回收等待时间秒
        final int coreSize = 5, maxSize = 10, queueCapacity = 1000, awaitSeconds = 60;

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadFactory(new DefaultThreadFactory(taskName));
        executor.setAwaitTerminationSeconds(awaitSeconds);
        // 配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(taskName + "-");
        executor.setRejectedExecutionHandler(rejectedExecutionHandler(taskName));
        executor.setWaitForTasksToCompleteOnShutdown(true);

        //执行初始化
        executor.initialize();
        return executor;
    }


    /**
     * 拒绝策略(可以定制个重试机制)
     * @param name
     * @return
     */
    private RejectedExecutionHandler rejectedExecutionHandler(String name) {
        return (runnable, executor) -> {
            final String executotName = name;
            if (!executor.isShutdown()) {
                try {
                    log.info("【队列阻塞】线程池 {} - {}", executotName, executor.toString());
                    executor.getQueue().put(runnable);
                } catch (InterruptedException e) {
                    log.warn(e.toString(), e);
                    runnable.run();
                }
            }
        };
    }


}
