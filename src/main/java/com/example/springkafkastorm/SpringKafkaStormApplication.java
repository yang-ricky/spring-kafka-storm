package com.example.springkafkastorm;

import com.example.springkafkastorm.storm.Topology;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringKafkaStormApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaStormApplication.class, args);
        Topology app = new Topology();
        String[] str = {"Storm Demo"};
        app.runStorm(str);
    }


}
