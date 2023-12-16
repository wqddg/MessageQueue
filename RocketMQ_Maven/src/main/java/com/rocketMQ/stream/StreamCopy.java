package com.rocketMQ.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.streams.core.RocketMQStream;
import org.apache.rocketmq.streams.core.rstream.StreamBuilder;
import org.apache.rocketmq.streams.core.serialization.KeyValueSerializer;
import org.apache.rocketmq.streams.core.topology.TopologyBuilder;
import org.apache.rocketmq.streams.core.util.Pair;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @Author: wqddg
 * @ClassName StreamCopy
 * @DateTime: 2023/12/13 11:14
 * @remarks : #
 */
public class StreamCopy {
    public  static void main(String[] args) {
        //构建流处理实例：一个JobId对应一个streamBuilder实例
        StreamBuilder streamBuilder = new StreamBuilder("StreamCopy");
        streamBuilder.source("sell",total->{
            String vales = new String(total, StandardCharsets.UTF_8);
            return new Pair<>(null,vales);
        })
                //sink方法：将结果输出到特定的topic
                .sink("sell_copy", new KeyValueSerializer<Object, String>() {
                    final ObjectMapper objectMapper=new ObjectMapper();
                    @Override
                    public byte[] serialize(Object o, String s) throws Throwable {
                        return objectMapper.writeValueAsBytes(s);
                    }
                });
        //一个StreamBuilder有一个TopologyBuilder，TopologyBuilder可构建出数据处理器
        TopologyBuilder build = streamBuilder.build();
        Properties properties=new Properties();
        properties.put(MixAll.NAMESRV_ADDR_PROPERTY,"192.168.128.138:9876");

        //RocketMQStream实例，有一个拓扑构建起TopologyBuilder可构建出数据处理器processor
        RocketMQStream rocketMQStream=new RocketMQStream(build,properties);
        Runtime.getRuntime().addShutdownHook(new Thread("start"){
            @Override
            public void run() {
                rocketMQStream.stop();
            }
        });
        rocketMQStream.start();
    }
}
