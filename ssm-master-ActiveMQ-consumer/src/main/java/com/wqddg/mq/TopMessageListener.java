package com.wqddg.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author: wqddg
 * @ClassName TopMessageListener
 * @DateTime: 2023/10/26 17:00
 * @remarks : #
 */
public class TopMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        try {
            System.out.println("topic:"+textMessage.getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }


    }
}
