package com.rocketMQ.oneDelayedSend;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ConsumerDelayedSend
 * @DateTime: 2023/10/31 14:26
 * @remarks : #
 */
public class ConsumerDelayedSend {
    public static void main(String[] args) throws MQClientException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("maven_wqddg");
        consumer.setNamesrvAddr("192.168.128.138:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(topic,"*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }
}
