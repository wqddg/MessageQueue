package com.rocketMQ.oneSendWay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: wqddg
 * @ClassName ConsumerOneWay
 * @DateTime: 2023/10/31 10:20
 * @remarks : #
 */
public class ConsumerOneWay {
    public static void main(String[] args) throws MQClientException, IOException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("maven_wqddg");
        consumer.setNamesrvAddr("192.168.128.138:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(topic,"*");
//        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues(topic);
//        Iterator<MessageQueue> iterator = messageQueues.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next().getQueueId()+"  "+
//                    iterator.next().getBrokerName());
//        }
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        System.out.println("消费者开始准备");
        consumer.start();
    }
}
