package com.rocketMQ.oneFilterSend;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: wqddg
 * @ClassName ProducerFilterSend
 * @DateTime: 2023/10/31 14:43
 * @remarks : #
 */
public class ProducerFilterSend {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        String topic="maven-java-topic";
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();
        String[] strings={"wqddg_1","wqddg_2"};
        for (int i = 0; i < 10; i++) {
            Message message=new Message(topic,strings[i%strings.length],"欢迎你".getBytes());
            defaultMQProducer.sendOneway(message);
        }
        defaultMQProducer.shutdown();
    }
}
