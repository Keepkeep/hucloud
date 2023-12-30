package com.expand.hucloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartitionInfo;

import java.util.Arrays;
import java.util.List;

/**
 * @author hdq
 * @time 2023/12/28 22:40
 */

@Slf4j
public class KafkaTopicUtil {


    public static boolean createTopic(AdminClient client,String topicName,int numPartitions,short replicationFactor){
        if(topicExists(client,topicName)){
            log.debug("topic:{}已经存在！", topicName);
            return false;
        }
        NewTopic topic = new NewTopic(topicName,numPartitions,replicationFactor);
        client.createTopics(Arrays.asList(new NewTopic[]{topic}));
        log.info("创建主题:{},partition:{},replicaFactor:{}", topicName, numPartitions, replicationFactor);
        return true;
    }

    public static boolean topicExists(AdminClient client,String topic){
        try{
            DescribeTopicsResult describeTopicsResult = client.describeTopics(Arrays.asList(topic.split(",")));
            KafkaFuture<TopicDescription> topicDescriptionKafkaFuture = describeTopicsResult.values().get(topic);
            TopicDescription topicDescription = topicDescriptionKafkaFuture.get();
            List<TopicPartitionInfo> partitions = topicDescription.partitions();
            if(CollectionUtils.isEmpty(partitions)){
                return false;
            }else{
                return true;
            }
        }catch(Exception e){

            return false;
        }

    }
}
