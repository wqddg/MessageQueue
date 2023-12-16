package com.wqddg.rabbit.helloword;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName Publish
 * @DateTime: 2023/11/22 16:06
 * @remarks : #
 */
public class Test {

    private static final String QUEUE_NAME="hello";

    @org.junit.Test
    public void publish()throws Exception{
        //1.获取连接对象
        Connection connection = RabbitMQConnectionUtils.getConnection();
        //2.构建channel
        Channel channel = connection.createChannel();
        //3.构建队列---交换机默认的
        //第一个参数是队列名称。
        //第二个参数是否是持久队列
        //第三个参数是否是排他队列--只能拥有一个消费者
        //第四个参数是长时间没有使用会被删除
        //第五个参数添加其他参数 这个在queue上有显示
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //4.发布消息
        String msg="hello word";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("消息发送成功");
    }

    @org.junit.Test
    public void consumer() throws IOException, TimeoutException {
        //1.获取连接对象
        Connection connection =  RabbitMQConnectionUtils.getConnection();
        //2.构建channel
        Channel channel = connection.createChannel();
        //3.构建队列----注意两个队列的值应该是一模一样的
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //4.接收消息
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者获取到的消息:"+new String(body));
            }
        });
        System.in.read();
    }



}
