package com.example.springbootactivemqproducer1.MQ;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Author: wqddg
 * @ClassName Consumer
 * @DateTime: 2023/10/28 16:48
 * @remarks : #
 */
@Component
public class Consumer {


    @JmsListener(destination = "springboot.queue",containerFactory = "jmsListenerContainerQueue")
    public void queueListenQueue(ActiveMQMessage activeMQMessage, Session session, String msg) throws JMSException {
        System.out.println("queue----获得数据消息："+msg);
        try {
            activeMQMessage.acknowledge();
        }catch (Exception e){
            session.recover();
        }
    }


    @JmsListener(destination = "springboot.topic",containerFactory = "jmsListenerContainerTopic")
    public void queueListenTopic(ActiveMQMessage activeMQMessage,Session session,String msg) throws JMSException {
        System.out.println("topic----获得数据消息："+msg);
        try {
            activeMQMessage.acknowledge();
        }catch (Exception e){
            session.recover();
        }
    }


}
