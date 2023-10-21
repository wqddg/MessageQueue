package com.wqddg.oneQueue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: wqddg
 * @ClassName Main_Producter
 * @DateTime: 2023/10/19 16:52
 * @remarks : #伤残者
 */
public class Main_Producter {

    public static void main(String[] args) throws JMSException {
        //在这里
        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory("tcp://192.168.128.138:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue("wqddg_queue");
        //创建消息
        MessageProducer producer = session.createProducer(queue);

        TextMessage textMessage = session.createTextMessage("你好");
        producer.send(textMessage);
        session.commit();
        session.close();
        connection.close();

    }
}
