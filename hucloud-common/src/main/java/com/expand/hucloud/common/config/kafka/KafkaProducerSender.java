package com.expand.hucloud.common.config.kafka;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.expand.hucloud.common.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author hdq
 * @time 2023/12/31 01:46
 */
@Slf4j
@Service
public class KafkaProducerSender {

    @Autowired(required = false)
    private KafkaProducer<String, String> producer;



    /**
     * 推送 kafka
     * @param topic
     * @param data
     */
    public void send(String topic,String data) {
        send(topic, data, null);
    }





    /**
     * 推送 kafka
     * @param topic
     * @param data
     * @param callback
     */
    @Async(AppConstant.DEFAULT_TASK)
    public void send(String topic, String data, Callback callback) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, IdUtil.fastSimpleUUID(), data);
        if (producer == null) {
            log.error("kafka producer is null...");
            return;
        }
        if (callback != null) {
            producer.send(record, callback);
        } else {
            producer.send(record, (metadata, e) -> {
                if (e != null) {
                    e.printStackTrace();
                    log.error("kafka sendMessage error:" + e.getMessage());
                }
                log.debug("kafka sendMessage success topic = {}, data = {}, partition={}, offset={}",
                        metadata.topic(), data, metadata.partition(), metadata.offset());
            });
        }
    }

}
