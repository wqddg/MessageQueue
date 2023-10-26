package com.wqddg.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author: wqddg
 * @ClassName MyMessageListener
 * @DateTime: 2023/10/26 16:40
 * @remarks : #
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
