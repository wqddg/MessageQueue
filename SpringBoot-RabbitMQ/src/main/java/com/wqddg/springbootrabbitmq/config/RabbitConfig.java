package com.wqddg.springbootrabbitmq.config;

//注意包名
import org.springframework.amqp.core.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: wqddg
 * @ClassName RabbitConfig
 * @DateTime: 2023/11/23 11:37
 * @remarks : #
 */

@SpringBootConfiguration
public class RabbitConfig {
    public static final String EXCHANGE="boot-exchange";
    public static final String QUEUE="boot-queue";
    public static final String ROUTING_KEY="*.wqddg.*";

    /**
     * 构建交换机
     * @return
     */
    @Bean
    public Exchange bootExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE).build();
    }

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue bootQueue(){
        return QueueBuilder.durable(QUEUE).build();
    }

    /**
     * 绑定 并且添加规则
     * @param bootExchange
     * @param bootQueue
     * @return
     */
    @Bean
    public Binding bootBinding(Exchange bootExchange,Queue bootQueue){
        return BindingBuilder.bind(bootQueue).to(bootExchange).with(ROUTING_KEY).noargs();
    }




}
