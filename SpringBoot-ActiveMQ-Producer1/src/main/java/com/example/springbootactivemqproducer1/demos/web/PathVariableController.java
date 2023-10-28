/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springbootactivemqproducer1.demos.web;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@RestController
public class PathVariableController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @RequestMapping("topic")
    public String topic(String msg){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("springboot.topic");
        jmsMessagingTemplate.convertAndSend(activeMQTopic,msg);
        return "成功了";
    }

    @RequestMapping("queue")
    public String queue(String msg){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("springboot.queue");
        jmsMessagingTemplate.convertAndSend(activeMQQueue,msg);
        return "成功了";
    }


    @RequestMapping("timeQueue")
    public String timeQueue(String msg,Integer time) throws JMSException {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("springboot.queue");
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(activeMQQueue);
        producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
        TextMessage textMessage = session.createTextMessage(msg);
        textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,time);
        producer.send(textMessage);
        session.close();
        connection.close();

        return "helo";
    }


    @RequestMapping("timeTopic")
    public String timeTopic(String msg,Integer time) throws JMSException {
        ActiveMQTopic activeMQQueue = new ActiveMQTopic("springboot.topic");
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer producer = session.createProducer(activeMQQueue);
        producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
        TextMessage textMessage = session.createTextMessage(msg);
        textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,time);
        producer.send(textMessage);
        session.close();
        connection.close();

        return "helo";
    }
}
