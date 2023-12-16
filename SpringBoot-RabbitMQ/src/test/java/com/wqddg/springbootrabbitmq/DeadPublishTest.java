package com.wqddg.springbootrabbitmq;

import com.wqddg.springbootrabbitmq.config.DeadLitterConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: wqddg
 * @ClassName DeadPublishTest
 * @DateTime: 2023/11/23 16:25
 * @remarks : #
 */
@SpringBootTest
public class DeadPublishTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void publish(){
        String message="这是我们的消息";
        rabbitTemplate.convertAndSend(DeadLitterConfig.NORMAL_EXCHANGE,"normal.a",message);
    }

    @Test
    public void publish_time(){
        String message="超时时间";
        rabbitTemplate.convertAndSend(DeadLitterConfig.NORMAL_EXCHANGE, "normal.a", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //ms级别的  超时时间
                message.getMessageProperties().setExpiration("5000");

                return message;
            }
        });
    }
}
