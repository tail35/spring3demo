package com.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "kafka")

public class MyKafkaConfig {
    public String bootstrap_servers;
    @Autowired
    public producerProperties producer;
    @Data
    @Configuration
    @ConfigurationProperties("kafka.producer")
    public static  class producerProperties {
        public String key_serializer;
        public String value_serializer;
        public String retries;
        public String batch_size;
        public String buffer_memory;
        public String acks;
    }
    @Autowired
    public consumerProperties consumer;
    @Data
    @Configuration
    @ConfigurationProperties("kafka.consumer")
    public static  class consumerProperties {
        public String enable_auto_commit;
        public String auto_commit_interval;
        public String group_id;
        public String auto_offset_reset;
        public String key_deserializer;
        public String value_deserializer;
    }
    @Autowired
    public templateProperties template;
    @Data
    @Configuration
    @ConfigurationProperties("kafka.template")
    public static  class templateProperties {
        public String default_topic;
    }

}
