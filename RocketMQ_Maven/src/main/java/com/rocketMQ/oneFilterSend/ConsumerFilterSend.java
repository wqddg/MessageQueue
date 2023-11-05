package com.rocketMQ.oneFilterSend;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ConsumerFilterSend
 * @DateTime: 2023/10/31 14:46
 * @remarks : #
 */
public class ConsumerFilterSend {
    public static void main(String[] args) throws MQClientException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("maven_wqddg");
        consumer.setNamesrvAddr("192.168.128.138:9876");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(topic,"wqddg_1");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("开始获取数据");
    }
}
