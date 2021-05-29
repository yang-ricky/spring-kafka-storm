package com.example.springkafkastorm.controller;


import com.example.springkafkastorm.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DemoController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/storm")
    public String storm(){
        kafkaProducerService.send();
        return "Storm";
    }
}
