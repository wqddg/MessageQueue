package com.wqddg.springbootrabbitmq;


import com.rabbitmq.client.Channel;
import com.wqddg.springbootrabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Author: wqddg
 * @ClassName ConsumerListener
 * @DateTime: 2023/11/23 12:44
 * @remarks : #
 */
@Configuration
public class ConsumerListener {



    @RabbitListener(queues = {RabbitConfig.QUEUE})
    public void consumer(Channel channel, Message message) throws IOException {
        System.out.println("获取传递过来的消息："+new String(message.getBody()));
        System.out.println("获取传递过来的消息："+new String(message.getMessageProperties().getCorrelationId()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
