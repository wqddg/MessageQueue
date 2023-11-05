package com.wqddg.utils;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @Author: wqddg
 * @ClassName RocketProducter
 * @DateTime: 2023/11/5 17:46
 * @remarks : #
 */
public class RocketProducter {
    private DefaultMQProducer mqProducer;

    private String produceGroup;

    private String nameAddr;

    public RocketProducter(String produceGroup,String nameAddr){
        this.produceGroup=produceGroup;
        this.nameAddr=nameAddr;
    }

    public void init() throws MQClientException {
        mqProducer=new DefaultMQProducer(produceGroup);
        mqProducer.setNamesrvAddr(nameAddr);
        mqProducer.start();
    }

    public void destroy(){
        mqProducer.shutdown();
    }

    public DefaultMQProducer getMqProducer() {
        return mqProducer;
    }
}
