package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName Publish_Exchange
 * @DateTime: 2023/11/23 15:17
 * @remarks : #
 */
public class Publish_Exchange {

    @Test
    public void publish_Exchange() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //队列持久化
        channel.queueDeclare("wqddg",true,false,false,null);
        String messge="这是我们的消息";
        //开启Confirms
        channel.confirmSelect();
        //设置confirms的异步回调
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息成功的发送到了Exchange");
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("消息未能成功的发送到了Exchange");
            }
        });

        //设置Return回调 确认消息是否路由到了Queue
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息没有路由到指定队列时，会调用此方法");
            }
        });
        //设置消息持久化
        AMQP.BasicProperties build = new AMQP.BasicProperties().builder()
                .deliveryMode(2)//消息会持久化
                .build();
        channel.basicPublish("","wqddg",true,build,messge.getBytes());

        System.in.read();
    }
}
