package com.wqddg.rabbit.WorkQueues;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName Test
 * @DateTime: 2023/11/22 16:34
 * @remarks : #
 */
public class WorkQueueTest {

    private static final String QUEUE_NAME="Work";
    @Test
    public void publish() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for (int i = 0; i < 10; i++) {
            String message="word hello  queue"+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        }
        System.out.println("发送成功");
    }

    @Test
    public void consumer_1() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //每次获取多少个？  打破轮询
        channel.basicQos(1);
        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("work模式接收_1"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
        System.in.read();
    }
    @Test
    public void consumer_2() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //每次获取多少个？  打破轮询
        channel.basicQos(1);


        channel.basicConsume(QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("work模式接收_2"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
        System.in.read();
    }
}
