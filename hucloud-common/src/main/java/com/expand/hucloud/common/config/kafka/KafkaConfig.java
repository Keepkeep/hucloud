package com.expand.hucloud.common.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author hdq
 * @time 2023/12/31 00:24
 * @description kafak 配置
 */

@Slf4j
@Configuration
public class KafkaConfig {

    @Autowired
    private OmofKafkaProperties omofKafkaProperties;


    @ConditionalOnProperty(
            prefix = "spring.http.encoding",
            value = {"enabled"},
            matchIfMissing = false
    )
    @Bean
    public KafkaProducer<String, String> getProducer() {
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProps());
        return producer;
    }

    /**
     * kafka 配置
     * @return
     */
    protected Properties getProducerProps(){

        if (StringUtils.isBlank(omofKafkaProperties.getBootstrapServers())){
            return null;
        }

        Properties props =  new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, omofKafkaProperties.getBootstrapServers());
        props.put(ProducerConfig.ACKS_CONFIG, omofKafkaProperties.getAcks());
        props.put(ProducerConfig.RETRIES_CONFIG, omofKafkaProperties.getRetries());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, omofKafkaProperties.getBatchSize());
        props.put(ProducerConfig.LINGER_MS_CONFIG, omofKafkaProperties.getLingerMs());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, omofKafkaProperties.getBufferMemory());

        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, omofKafkaProperties.getMaxRequestSize());

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        if (omofKafkaProperties.getKafkaSecurityStatus()){
            props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put("sasl.jaas.config",
                    "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+omofKafkaProperties.getUserName()+"\" password=\""+omofKafkaProperties.getPassword()+"\";");
        }

        return props;
    }

}
