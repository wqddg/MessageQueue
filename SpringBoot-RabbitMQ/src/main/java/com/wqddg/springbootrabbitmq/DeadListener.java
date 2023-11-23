package com.wqddg.springbootrabbitmq;

import com.rabbitmq.client.Channel;
import com.wqddg.springbootrabbitmq.config.DeadLitterConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: wqddg
 * @ClassName DeadListener
 * @DateTime: 2023/11/23 16:28
 * @remarks : #
 */
@Component
public class DeadListener {


//    @RabbitListener(queues = {DeadLitterConfig.NORMAL_QUEUE})
//    public void message(Channel channel, Message message) throws IOException {
//        System.out.println("获取到的数据"+new String(message.getBody()));
//        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
//        //以上两种二选一
//
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//
//    }
//
}
