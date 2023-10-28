package com.example.springbootactivemqproducer1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * @Author: wqddg
 * @ClassName ActiveMQConfig
 * @DateTime: 2023/10/28 16:31
 * @remarks : #配置文件
 */
@EnableJms
@Configuration
public class ActiveMQConfig {



    @Value("${spring.activemq.broker-url}")
    private String broURL;

    private String queue="springboot.queue";

    private String topic="springboot.topic";

    @Bean
    public Queue actQueue(){
        return new ActiveMQQueue(queue);
    }

    @Bean
    public Topic actTopic(){
        return new ActiveMQTopic(topic);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory(broURL);
    }

    /**
     * queue模式
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory=new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setSessionTransacted(false);
        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(4);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;
    }

    /**
     * 消息重发规则
     * @return
     */
    private RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy=new RedeliveryPolicy();
        redeliveryPolicy.setUseExponentialBackOff(true);
        redeliveryPolicy.setMaximumRedeliveries(6);
        redeliveryPolicy.setInitialRedeliveryDelay(1000);
        redeliveryPolicy.setBackOffMultiplier(2);
        redeliveryPolicy.setUseCollisionAvoidance(false);
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }

    /**
     * 订阅发布模式
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory=new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setSessionTransacted(false);
        defaultJmsListenerContainerFactory.setSessionAcknowledgeMode(4);
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        return defaultJmsListenerContainerFactory;

    }




}
