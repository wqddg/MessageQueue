package com.wqddg.springbootrabbitmq;

import com.wqddg.springbootrabbitmq.config.RabbitConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                if (b){
                    System.out.println("消息已经送达到了交换机");
                }else {
                    System.out.println("消息还没有送达交换机");
                }
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                //路由失败了
                String msg=new String(returnedMessage.getMessage().getBody());
                System.out.println("保留到queue中失败"+msg);
            }
        });
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "big.wqddg.s", "message", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //消息持久化
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }
        });
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
