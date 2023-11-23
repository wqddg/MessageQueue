package com.wqddg.springbootrabbitmq;

import com.wqddg.springbootrabbitmq.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wqddg
 * @ClassName PulisherTest
 * @DateTime: 2023/11/23 12:37
 * @remarks : #
 */
@SpringBootTest
public class PulisherTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    @Test
    public void publish(){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"big.wqddg.s","message");
        System.out.println("消息发送成功");
    }


    /**
     * 发送消息 并且传输数据
     */
    @Test
    public void publishWithProps(){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "big.wqddg.s", "message", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setCorrelationId("123456");
                return message;
            }
        });
        System.out.println("消息发送成功");
    }



}
