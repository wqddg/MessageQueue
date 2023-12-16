package com.wqddg.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wqddg
 * @ClassName DeadayedConfig
 * @DateTime: 2023/11/23 17:21
 * @remarks : #
 */
@Configuration
public class DelayedConfig {

    private static final String DELAY_EXCHANGE="delay-exchange";
    private static final String DELAY_QUEUE="delay-QUEUE";
    private static final String DELAY_ROUTING_KEY="delay.#";


    @Bean
    public Exchange delayedExchange(){
        Map<String, Object> arguments=new HashMap<>();
        arguments.put("x-delayed-type","topic");//类型
        Exchange exchange=new CustomExchange(DELAY_EXCHANGE,"x-delayed-message",true,false,arguments);
        return exchange;
    }

    @Bean
    public Queue delayed(){
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }

    @Bean
    public Binding binding(Queue delayed,Exchange delayedExchange){
        return BindingBuilder.bind(delayed).to(delayedExchange).with(DELAY_ROUTING_KEY).noargs();
    }
}
