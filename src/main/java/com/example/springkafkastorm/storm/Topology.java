package com.example.springkafkastorm.storm;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.topology.TopologyBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Topology {
    public void runStorm(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(Constants.KAFKA_SPOUT, new Spout(), 1);
        builder.setBolt(Constants.INSERT_BOLT, new Bolt(), 1)
                .setNumTasks(1)
                .shuffleGrouping(Constants.KAFKA_SPOUT);
        Config config = new Config();
        config.put(Config.NIMBUS_SEEDS, Arrays.asList("47.103.138.95"));
        config.put(Config.NIMBUS_THRIFT_PORT, 6627);
        config.put(Config.STORM_ZOOKEEPER_SERVERS, Arrays.asList("47.103.138.95"));
        config.put(Config.STORM_ZOOKEEPER_PORT, 2181);
        config.setDebug(true);
        config.setNumWorkers(1);

        //System.setProperty("storm.jar","~/Code/ricky.jar");

        //一个应答者
        config.setNumAckers(1);
        config.setNumWorkers(1);

        try {
            if (args != null && args.length > 0) {
                // 远程模式
                System.out.println("远程模式");
                StormSubmitter.submitTopology(args[0], config, builder.createTopology());
            } else {
                // 本地模式
                System.out.println("本地模式");
                LocalCluster cluster = new LocalCluster();
                cluster.submitTopology("TopologyApp", config, builder.createTopology());
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("storm启动失败");
        }

    }
}
