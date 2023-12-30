package com.expand.hucloud.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author hdq
 * @time 2023/12/30 23:09
 */

@Slf4j
@Configuration
public class ApplicationListener {

    @EventListener
    public void appStartEvent(ApplicationStartedEvent event) {
        log.info("Application started");
        //todo  预处理一些数据

    }

    @EventListener
    public void appCloseEvent(ContextClosedEvent event) {
        log.info("Application closed");
        //关闭操作
    }
}
