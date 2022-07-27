package com.controller;

import com.config.MyKafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Configuration
@EnableKafka

public class kafkaReceiver {
    @Autowired
    public MyKafkaConfig mMyKafkaConfig;
    @Bean
    public  ConcurrentKafkaListenerContainerFactory<String,String> Consume(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,mMyKafkaConfig.bootstrap_servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"test-consumer-group");//from kafka's config/consumer.properties

        ConsumerFactory<String,String> cf = new DefaultKafkaConsumerFactory<>( props );

        ConcurrentKafkaListenerContainerFactory<String,String> factory =new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(cf);
        return factory;
    }
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(groupId = "test-consumer-group",topics = "mytest")
    public void receive(String payload) {
        //log.info("received payload='{}'",payload);
        latch.countDown();
    }
}
