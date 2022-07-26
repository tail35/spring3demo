package com.controller;

import com.config.MyKafkaConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaSender {
    public String test="111";

    @Autowired
    public MyKafkaConfig mMyKafkaConfig;
    @Bean
    public KafkaTemplate<String,String>  kafkaTemplate(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,mMyKafkaConfig.bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, mMyKafkaConfig.producer.key_serializer );
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, mMyKafkaConfig.producer.value_serializer);
        props.put(ProducerConfig.ACKS_CONFIG,"0");
        ProducerFactory<String,String> producerfactor = new DefaultKafkaProducerFactory<>(props);
        return new KafkaTemplate<String, String>(producerfactor);
    }
}
