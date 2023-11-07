package com.wqddg.springbootrocketmq.server;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @Author: wqddg
 * @ClassName RocketServer
 * @DateTime: 2023/11/7 15:26
 * @remarks : #
 */
@Service
@RocketMQMessageListener(consumerGroup = "springGroup",topic = "springTopic")
public class RocketServer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("获取到Rocket发送过来的指："+s);

    }
}
