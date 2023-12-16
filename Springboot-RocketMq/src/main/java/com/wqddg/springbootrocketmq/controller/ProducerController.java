package com.wqddg.springbootrocketmq.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: wqddg
 * @ClassName ProducerController
 * @DateTime: 2023/11/7 15:24
 * @remarks : #
 */
@RestController
@RequestMapping("rocket")
public class ProducerController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @GetMapping("send")
    public String send(String name){
        rocketMQTemplate.convertAndSend("springTopic",name);

        return "OK";
    }
}
