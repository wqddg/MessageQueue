package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName PublishSubscribe_Fabout
 * @DateTime: 2023/11/22 16:57
 * @remarks : #
 */
public class PublishSubscribe_Fabout {

    public static final String EXCHANGE_NAME="publish_fabout";
    public static final String QUEUE_NAME_1="queue_fabout_1";
    public static final String QUEUE_NAME_2="queue_fabout_2";

    @Test
    public void publish()throws Exception{
        //1.获取连接对象
        Connection connection = RabbitMQConnectionUtils.getConnection();
        //2.获取通道
        Channel channel = connection.createChannel();
        //3.构建交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        //4.构建队列
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        //5.绑定交换机队列  使用的是FANOUT类型的交换机  直接绑定
        channel.queueBind(QUEUE_NAME_1,EXCHANGE_NAME,"");
        channel.queueBind(QUEUE_NAME_2,EXCHANGE_NAME,"");
        //6.发送消息到交换机
        channel.basicPublish(EXCHANGE_NAME,"wqddg",null,"欢迎你 你好".getBytes());
        System.out.println("消息发送成功");

    }

    @Test
    public void consumer_1() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_1,false,false,false,null);
        channel.basicConsume(QUEUE_NAME_1,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取到的值是："+new String(body));
            }
        });
        System.in.read();

    }

    @Test
    public void consumer_2() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME_2,false,false,false,null);
        channel.basicConsume(QUEUE_NAME_2,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取到的值是："+new String(body));
            }
        });
        System.in.read();

    }
}
