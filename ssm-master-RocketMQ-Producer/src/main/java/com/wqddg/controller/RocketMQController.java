package com.wqddg.controller;

import com.wqddg.utils.RocketProducter;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wqddg
 * @ClassName RocketMQController
 * @DateTime: 2023/11/5 17:53
 * @remarks : #
 */
@RequestMapping("rocket")
@RestController
public class RocketMQController {

    @Value("TAG1")
    private String tag;

    @Value("SSMTopic")
    private String topic;

    @Autowired
    @Qualifier("rocketProducter")
    private RocketProducter rocketProducter;
    @GetMapping("test")
    public String hello() throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        for (int i = 0; i <5; i++) {
            Message message=new Message(topic,tag,"hellio".getBytes());
            SendResult send = rocketProducter.getMqProducer().send(message);
            System.out.println(send);
        }
        return "hello";
    }



}
