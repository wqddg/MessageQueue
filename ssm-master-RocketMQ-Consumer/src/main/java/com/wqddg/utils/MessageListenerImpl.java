package com.wqddg.utils;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName MessageListenerImpl
 * @DateTime: 2023/11/5 19:14
 * @remarks : #
 */
public class MessageListenerImpl implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            System.out.println(new String(msg.getBody())+">>>>>");
        }
        System.out.println("hello");
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
