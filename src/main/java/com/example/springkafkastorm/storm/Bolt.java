package com.example.springkafkastorm.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

public class Bolt extends BaseRichBolt {

    @Override
    public void prepare(Map map, TopologyContext context, OutputCollector collector) {

    }

    @Override
    public void execute(Tuple tuple) {
        String msg = tuple.getStringByField(Constants.FIELD);
        System.out.println(msg + "from Bolt");
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer arg) {

    }
}
