package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName RPC
 * @DateTime: 2023/11/23 10:47
 * @remarks : #
 */
public class RPC {
    private static final String QUEUE_RCP_1="RCP_1";
    private static final String QUEUE_RCP_2="RCP_2";


    @Test
    public void publish()throws Exception{
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_RCP_1,false,false,false,null);
        channel.queueDeclare(QUEUE_RCP_2,false,false,false,null);
        String uuid= UUID.randomUUID().toString();
        String mes="RPC的方式";
        AMQP.BasicProperties build = new AMQP.BasicProperties()
                .builder()
                .replyTo(QUEUE_RCP_2)//发送到的队列
                .correlationId(uuid).build();
        channel.basicPublish("",QUEUE_RCP_1,build,mes.getBytes());

        channel.basicConsume(QUEUE_RCP_2,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String correlationId = properties.getCorrelationId();
                if (correlationId!=null&&uuid.equals(correlationId)){
                    System.out.println("接收到服务端的响应:"+new String(body));
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        });
        System.out.println("消息发送成功");
        System.in.read();
    }



    @Test
    public void consumer() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_RCP_2,false,false,false,null);
        channel.queueDeclare(QUEUE_RCP_1,false,false,false,null);
        channel.basicConsume(QUEUE_RCP_1,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取到服务器发送来的消息："+new String(body));
                String resp="获取到队列中的信息";
                String replyTo = properties.getReplyTo();
                String correlationId = properties.getCorrelationId();
                AMQP.BasicProperties build = new AMQP.BasicProperties().builder()
                        .correlationId(correlationId).build();
                channel.basicPublish("",replyTo,build,resp.getBytes());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
        System.in.read();



    }

}
