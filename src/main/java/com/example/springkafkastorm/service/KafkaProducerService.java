package com.example.springkafkastorm.service;

import com.alibaba.fastjson.JSON;
import com.example.springkafkastorm.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        for (int i=0; i<5; i++){
            Message message = new Message();
            message.setTaskId(System.currentTimeMillis());
            message.setMsg(UUID.randomUUID().toString() + " ..... " + i + " from ricky's kafka");
            message.setSendTime(new Date().toString());
            //kafkaTemplate.send("user", JSON.toJSONString(message));
            kafkaTemplate.send("user", "Message just for storm");
        }
    }
}
