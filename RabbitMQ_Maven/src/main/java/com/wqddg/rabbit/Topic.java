package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName Topic
 * @DateTime: 2023/11/22 17:37
 * @remarks : #
 */
public class Topic {
    private static final String echannel="topics";
    private static final String queue_1="topics_queue1";
    private static final String queue_2="topics_queue2";

    @Test
    public void publsh() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(echannel, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(queue_1,false,false,false,null);
        channel.queueDeclare(queue_2,false,false,false,null);

        //topic类型的交换机和队列绑定时，需要以aaa.bbb.ccc...方式编写routingKey
        //其中有两个特殊字符：*(相当于占位符) #(相当于通配符)
        channel.queueBind(queue_1,echannel,"*.orange.*");
        channel.queueBind(queue_2,echannel,"*.*.rabbit");
        channel.queueBind(queue_1,echannel,"lazy.#");

        channel.basicPublish(echannel,"wqddg.name.rabbit",null,"wqddg.name.rabbit".getBytes());
        channel.basicPublish(echannel,"wqddg.orange.wqddg",null,"wqddg.orange.wqddg".getBytes());
        channel.basicPublish(echannel,"lazy.hello",null,"lazy.hello".getBytes());
        System.out.println("获取成功");

    }


    @Test
    public void sumer_1() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_1,false,false,false,null);
        channel.basicConsume(queue_1,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的值是："+new String(body));
            }
        });
        System.in.read();
    }
    @Test
    public void sumer_2() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(queue_2,false,false,false,null);
        channel.basicConsume(queue_2,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的值是："+new String(body));
            }
        });
        System.in.read();
    }

}
