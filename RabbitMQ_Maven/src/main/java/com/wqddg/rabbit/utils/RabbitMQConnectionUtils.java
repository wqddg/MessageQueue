package com.wqddg.rabbit.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: wqddg
 * @ClassName RabbitMQConnctionUtils
 * @DateTime: 2023/11/22 15:56
 * @remarks : #
 */
public class RabbitMQConnectionUtils {

    private static final String RABBItMQ_IP="192.168.128.138";
    private static final int RABBItMQ_Port=5672;
    private static final String RABBItMQ_USERNAME="guest";
    private static final String RABBItMQ_PASSWORD="guest";
    private static final String RABBItMQ_VIRTAUL="/";



    /**
     * 构建RabbitMq的连接对象
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //插件Connection工厂
        ConnectionFactory connectionFactory=new ConnectionFactory();

        //设置RabbitMQ的属性消息
        connectionFactory.setHost(RABBItMQ_IP);
        connectionFactory.setPassword(RABBItMQ_PASSWORD);
        connectionFactory.setUsername(RABBItMQ_USERNAME);
        connectionFactory.setVirtualHost(RABBItMQ_VIRTAUL);
        connectionFactory.setPort(RABBItMQ_Port);
        //获取连接
        return connectionFactory.newConnection();
    }


}
