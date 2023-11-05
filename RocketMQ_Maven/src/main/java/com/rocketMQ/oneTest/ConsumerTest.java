package com.rocketMQ.oneTest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName CosumerTest
 * @DateTime: 2023/10/31 12:04
 * @remarks : #
 */
public class ConsumerTest {
    public static void main(String[] args) throws MQClientException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer mqPushConsumer=new DefaultMQPushConsumer("maven_wqddg");
        mqPushConsumer.setNamesrvAddr("192.168.128.138:9876");
        mqPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        mqPushConsumer.subscribe(topic,"*");
        mqPushConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }


                return ConsumeOrderlyStatus.SUCCESS;
            }
        });


        mqPushConsumer.start();
        System.out.println("开始获取数据");
    }
}
