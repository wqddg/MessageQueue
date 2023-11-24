package com.wqddg.rabbit;

import com.rabbitmq.client.*;
import com.wqddg.rabbit.utils.RabbitMQConnectionUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName HeadersExchange
 * @DateTime: 2023/11/24 10:50
 * @remarks : #
 */
public class HeadersExchange {
    private static final String HEADER_EXCHANGE="headerExchange";
    private static final String HEADER_QUEUE="headerQueue";


    public static void main(String[] args)throws Exception {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        //构建交换机和队列，并基于Header的方式
        channel.exchangeDeclare(HEADER_EXCHANGE, BuiltinExchangeType.HEADERS);
        channel.queueDeclare(HEADER_QUEUE,false,false,false,null);
        Map<String, Object> arguments=new HashMap<>();
//        arguments.put("x-match","all"); //all代表与的关系
        arguments.put("x-match","any"); //any 代表或的关系
        arguments.put("name","wqddg");
        arguments.put("age","23");
        //这里可以绑定多个  条件不同
        channel.queueBind(HEADER_QUEUE,HEADER_EXCHANGE,"",arguments);
        String meg="测试Header的传递方案";
        Map<String, Object> headers=new HashMap<>();
        headers.put("name","wqddg");
        headers.put("age","23");
        AMQP.BasicProperties props=new AMQP.BasicProperties.Builder()
                .headers(headers)
                .build();
        channel.basicPublish(HEADER_EXCHANGE,HEADER_QUEUE,props,meg.getBytes());
    }




    @Test
    public void consmer() throws IOException, TimeoutException {
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(HEADER_QUEUE,false,false,false,null);
        channel.basicConsume(HEADER_QUEUE,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取的值"+new String(body));
            }
        });
        System.in.read();
    }
}
