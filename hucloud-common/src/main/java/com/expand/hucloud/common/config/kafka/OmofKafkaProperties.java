package com.expand.hucloud.common.config.kafka;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hdq
 * @time 2023/12/31 00:24
 * @description kafak
 */
@Slf4j
@Configuration
@ConfigurationProperties(prefix="hucloud-application.kafka")
@Data
public class OmofKafkaProperties {


    private String bootstrapServers;

    private Boolean kafkaSecurityStatus = false;


    private String userName;


    private String password;

    /*********************Consumer*************************/

    private String groupId;

    private String autoOffsetReset = "earliest";

    private Boolean enableAutoCommit = false;

    private String autoCommitIntervalMs = "1000";

    private Integer maxPollRecords = 50;

    //timeout
    private Integer maxTimeout = 500;


    /*********************Consumer*************************/

    private String acks = "1";

    private String retries = "1";

    private String batchSize = "16384";

    private String lingerMs = "1";

    private String bufferMemory = "33554432";

    /** KAFKA默认1M, MDIAP-API默认5M**/
    private String maxRequestSize = "5242880";

    private Integer partition = 3;


}
