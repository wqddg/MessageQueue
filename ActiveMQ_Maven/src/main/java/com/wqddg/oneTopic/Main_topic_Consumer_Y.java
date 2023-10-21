package com.wqddg.oneTopic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: wqddg
 * @ClassName Main_topic_Consumer_Y
 * @DateTime: 2023/10/21 11:00
 * @remarks : #
 */
public class Main_topic_Consumer_Y {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.128.138:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("wqddg_topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> {
            TextMessage textMessage= (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
                textMessage.acknowledge();
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
