package com.example.springkafkastorm.entity;

import lombok.Data;

@Data
public class Message {
    private Long taskId;

    private String sendTime;

    private String Msg;
}
