package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName Routing
 * @DateTime: 2023/11/22 17:24
 * @remarks : #
 */
public class Routing {
    private static final String EXCHANGE="RoutingVM";
    private static final  String QUEUE_NAME_1="RoutingQUEUE_NAME_1";
    private static final  String QUEUE_NAME_2="RoutingQUEUE_NAME_2";



    @Test
    public void publish() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        //按照条件  进行绑定
        channel.queueBind(QUEUE_NAME_1,EXCHANGE,"orange");
        channel.queueBind(QUEUE_NAME_2,EXCHANGE,"black");
        channel.queueBind(QUEUE_NAME_2,EXCHANGE,"green");
        channel.basicPublish(EXCHANGE,"orange",null,"routing--orange".getBytes());
        channel.basicPublish(EXCHANGE,"black",null,"routing--black".getBytes());
        channel.basicPublish(EXCHANGE,"green",null,"routing--green".getBytes());
        channel.basicPublish(EXCHANGE,"orange",null,"routing--orange".getBytes());
        System.out.println("消息发送成功");
    }

    @Test
    public void sumer_1() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        channel.basicConsume(QUEUE_NAME_1,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的值是"+new String(body));
            }
        });
        System.in.read();
    }

    @Test
    public void sumer_2() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        channel.basicConsume(QUEUE_NAME_2,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的值是"+new String(body));
            }
        });
        System.in.read();
    }
}
