package com.expand.hucloud.common.config.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hdq
 * @time 2023/12/31 00:24
 * @description kafak
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaTemplateSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息
     *
     * @param topicName 主题
     *                  message 发送消息
     * @return void
     */
    public void send(String topicName, String message) {
        try {
            kafkaTemplate.send(topicName, message).addCallback(endResult -> {
                if (log.isDebugEnabled()) {
                    log.debug("发送主题[{}]消息：{},成功！", topicName, message);
                }
            }, throwable -> log.error("发送主题[{}]消息：{},失败，原因：{}", topicName, message, throwable.getMessage()));
        } catch (Exception e) {
            log.error("发送主题[{}]消息出现异常", topicName);
        }
    }
}
