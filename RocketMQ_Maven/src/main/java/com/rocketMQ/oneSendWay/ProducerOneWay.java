package com.rocketMQ.oneSendWay;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: wqddg
 * @ClassName Producter
 * @DateTime: 2023/10/31 9:59
 * @remarks : # 发送者
 */
public class ProducerOneWay {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        String topic="maven-java-topic";
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();
        for (int i = 0; i < 10; i++) {
            Message message=new Message(topic,"hello ".getBytes());
            defaultMQProducer.sendOneway(message);
        }
        defaultMQProducer.shutdown();
    }
}
