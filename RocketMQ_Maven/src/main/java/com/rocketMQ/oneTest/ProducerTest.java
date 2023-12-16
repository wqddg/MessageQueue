package com.rocketMQ.oneTest;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ProducerTest
 * @DateTime: 2023/10/31 11:52
 * @remarks : #
 */
public class ProducerTest {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        String topic="maven-java-topic";
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();

        List<MessageQueue> messageQueues = defaultMQProducer.fetchPublishMessageQueues(topic);
        System.out.println(messageQueues.size()+"个数");

        List<Order> list = UtilsList.orderList();
        for (int i = 0; i < list.size(); i++) {
            String orderString = list.get(i).toString();
            Message message=new Message(topic,orderString.getBytes());
            defaultMQProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Long id= (Long) arg;
                    long l = id % mqs.size();
                    return mqs.get(Math.toIntExact(l));
                }
            },list.get(i).getOrderId());
        }

        defaultMQProducer.shutdown();
    }
}
