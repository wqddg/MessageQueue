package com.rocketMQ.oneSendAsyn;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ConsumerSendAsyn
 * @DateTime: 2023/10/31 11:26
 * @remarks : #
 */
public class ConsumerSendAsyn {
    public static void main(String[] args) throws MQClientException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer defaultMQPushConsumer=new DefaultMQPushConsumer("maven_wqddg");
        defaultMQPushConsumer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQPushConsumer.subscribe(topic,"*");
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });


        defaultMQPushConsumer.start();
        System.out.println("获取数据");

    }
}
