package com.rocketMQ.oneSend;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName ConsumerSend
 * @DateTime: 2023/10/31 11:16
 * @remarks : #
 */
public class ConsumerSend {
    public static void main(String[] args) throws MQClientException {
        String topic="maven-java-topic";
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("maven_wqddg");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setNamesrvAddr("192.168.128.138:9876");
        consumer.subscribe(topic,"*");
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(msg.getMsgId()+""+new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
        System.out.println("开始获取数据");

    }
}
