package com.rocketMQ.oneSend;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: wqddg
 * @ClassName Producer
 * @DateTime: 2023/10/31 11:07
 * @remarks : #
 */
public class ProducerSend {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        String topic="maven-java-topic";

        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();
        for (int i = 0; i < 20; i++) {
            Message message= new Message(topic,(i+"hello").getBytes());
            SendResult send = defaultMQProducer.send(message);
            System.out.println(send);
        }
        defaultMQProducer.shutdown();
    }
}
