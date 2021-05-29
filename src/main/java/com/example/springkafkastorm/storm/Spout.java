package com.example.springkafkastorm.storm;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class Spout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;

    @SuppressWarnings("rawtypes")
    @Override
    public void open(Map mp, TopologyContext context, SpoutOutputCollector collector) {
        InitKafka();
        System.out.println("ricky init kafka hahahahahaha");
        this.collector = collector;
    }

    @Override
    public void nextTuple() {
        while(true) {
            msgList = consumer.poll(100);
            if (null != msgList && !msgList.isEmpty()) {
                System.out.println("接受到数据");
            } else {
                System.out.println("没有数据");
            }

        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields(Constants.FIELD));
    }

    private void InitKafka() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "8.141.54.77:9094");
        props.put("max.poll.records", 100);
        props.put("enable.auto.commit", false);
        props.put("group.id", "group-test");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<String, String>(props);
        this.consumer.subscribe(Arrays.asList("user"));
    }
}
