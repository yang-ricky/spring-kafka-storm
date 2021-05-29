package com.example.springkafkastorm.service;

import com.alibaba.fastjson.JSONObject;
import com.example.springkafkastorm.entity.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = {"user"})
    public void listen(ConsumerRecord<String,String> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println("--------------record=" + record);
            System.out.println("--------------message=" + message);
            Message msg = JSONObject.toJavaObject(JSONObject.parseObject((String) message), Message.class);
            System.out.println("-------message send time:" + msg.getMsg());
        }
    }
}
