package com.controller;

import com.config.MyJdbcConfig;
import com.config.MyKafkaConfig;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
//import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.adapter.BatchToRecordAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class MyKafka {


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public MyKafkaConfig mMyKafkaConfig;

    public KafkaTemplate<String,String>  mykafkaTemplate(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,mMyKafkaConfig.bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, mMyKafkaConfig.producer.key_serializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, mMyKafkaConfig.producer.value_serializer);
        props.put(ProducerConfig.ACKS_CONFIG,"0");


        ProducerFactory<String,String> producerfactor = new DefaultKafkaProducerFactory<>(props);
        return new KafkaTemplate<String, String>(producerfactor);
    }

    @GetMapping("/kafka")
    public String MyKafka(){

        //sender
        String test="rrrr";
        //log.info("sending payload='{}' to topic='{}'",test,mMyKafkaConfig.template.default_topic);
//        try {
//            this.kafkaTemplate.send(mMyKafkaConfig.template.default_topic, test);
//        }catch (Exception e)
//        {
//            String err = e.toString();
//            String err2 = e.getMessage();
//            int k=0;
//            k++;
//        }
        try {
//            Integer par=1;
//            ProducerRecord<String, String> producerRecord = new ProducerRecord(
//                    "mytest", // topic
//                    par,         // 不指定partition
//                    System.currentTimeMillis(), // 发送消息的事件戳
//                    null,               // key不指定
//                    "rrr",  // msg
//                    new RecordHeaders().add(new RecordHeader("ttl", "20".getBytes(StandardCharsets.UTF_8)))
//            );
//            mykafkaTemplate().send(producerRecord);
            mykafkaTemplate().send(mMyKafkaConfig.template.default_topic, test);
        }catch ( Exception e ){
            String err = e.toString();
            String err2 = e.getMessage();
            int k=0;
            k++;
        }
        return "111";
    }
}
