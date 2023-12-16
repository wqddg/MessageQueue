package com.rocketMQ.oneDelayedSend;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: wqddg
 * @ClassName ProducerDelayedSend
 * @DateTime: 2023/10/31 14:26
 * @remarks : #
 */
public class ProducerDelayedSend {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        String topic="maven-java-topic";
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();
        for (int i = 0; i < 10; i++) {
            Message message=new Message(topic,"hello  good AfterNoon".getBytes());
            message.setDelayTimeLevel(4);
            SendResult send = defaultMQProducer.send(message);
            System.out.println(send);
        }

        defaultMQProducer.shutdown();
    }
}
