package com.wqddg.oneTopic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: wqddg
 * @ClassName Main_Topic_Produceter
 * @DateTime: 2023/10/21 10:54
 * @remarks : #
 */
public class Main_Topic_Produceter {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.128.138:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("wqddg_topic");
        MessageProducer producer = session.createProducer(topic);

        producer.send(session.createTextMessage("hello  why?"));
        session.commit();
        session.close();
        connection.close();


    }
}
