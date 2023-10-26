package com.wqddg;

import com.wqddg.entity.Account;
import com.wqddg.server.IAccount;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName Test
 * @DateTime: 2023/8/1 19:11
 * @remarks : #
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Test {


    @Autowired
    private IAccount iAccount;



    @org.junit.Test
    public void test_1(){
        iAccount.findAll().stream().forEach(System.out::println);
    }
    @Resource
    private JmsTemplate jmsTemplate;

    @Resource
    private ActiveMQQueue activeMQQueue;

    @Resource
    private ActiveMQTopic activeMQTopic;


    /**
     * 同时发送Queue 和topic
     */
    @org.junit.Test
    public void ActiveMQ(){
        jmsTemplate.send(activeMQQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ssm的方式发送数据queue");
            }
        });

        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ssm的方式发送数据topic");
            }
        });
    }
}
