package com.wqddg.oneTopic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: wqddg
 * @ClassName Main_Topic_Consumer
 * @DateTime: 2023/10/21 10:57
 * @remarks : #
 */
public class Main_Topic_Consumer {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.128.138:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("wqddg_topic");
        MessageConsumer consumer = session.createConsumer(topic);
        while (true){
            TextMessage receive = (TextMessage) consumer.receive(1000);
            if (receive!=null){
                System.out.println(receive.getText());
            }

        }
    }
}
