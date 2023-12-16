package com.wqddg.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wqddg
 * @ClassName DeadLitterConfig
 * @DateTime: 2023/11/23 16:13
 * @remarks : #
 */
@Configuration
public class DeadLitterConfig {
    //普通队列

    public static final String NORMAL_EXCHANGE="normal-exchange";
    public static final String NORMAL_QUEUE="normal-queue";
    public static final String NORMAL_ROUTING_KEY="normal.#";

    //死信队列
    public static final String DEAD_EXCHANGE="dead-exchange";
    public static final String DEAD_QUEUE="dead-queue";
    public static final String DEAD_ROUTING_KEY="dead.#";


    @Bean
    public Exchange normalExchange(){
        return ExchangeBuilder.topicExchange(NORMAL_EXCHANGE).build();
    }

    @Bean
    public Queue normalQueue(){
        //绑定死信交换机  并且设置路由key
        return QueueBuilder.durable(NORMAL_QUEUE).deadLetterExchange(DEAD_EXCHANGE).deadLetterRoutingKey("dead.abc").maxPriority(1).build();
    }

    @Bean
    public Binding normalBindIng(Exchange normalExchange,Queue normalQueue){
        return BindingBuilder.bind(normalQueue).to(normalExchange).with(NORMAL_ROUTING_KEY).noargs();
    }
    //开始创建死信所需的交换机和队列
    @Bean
    public Exchange deadExchange(){
        return ExchangeBuilder.topicExchange(DEAD_EXCHANGE).build();
    }

    @Bean
    public Queue deadQueue(){
        //绑定死信交换机
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    @Bean
    public Binding deadBindIng(Exchange deadExchange,Queue deadQueue){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_ROUTING_KEY).noargs();
    }







}
