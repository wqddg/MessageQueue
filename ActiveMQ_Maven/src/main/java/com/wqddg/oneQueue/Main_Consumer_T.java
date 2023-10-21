package com.wqddg.oneQueue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: wqddg
 * @ClassName Main_Consumer_T
 * @DateTime: 2023/10/21 10:48
 * @remarks : #
 */
public class Main_Consumer_T {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.128.138:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("wqddg_queue");
        MessageConsumer consumer = session.createConsumer(queue);
        while (true){
            TextMessage receive = (TextMessage) consumer.receive(1000);
            if (receive!=null){
                System.out.println(receive.getText());
            }

        }


    }
}
