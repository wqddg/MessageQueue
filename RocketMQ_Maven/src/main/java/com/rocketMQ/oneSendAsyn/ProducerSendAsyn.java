package com.rocketMQ.oneSendAsyn;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author: wqddg
 * @ClassName ProducerSendAsyn
 * @DateTime: 2023/10/31 11:22
 * @remarks : #
 */
public class ProducerSendAsyn {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        String topic="maven-java-topic";
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer("maven_wqddg");
        defaultMQProducer.setNamesrvAddr("192.168.128.138:9876");
        defaultMQProducer.setSendLatencyFaultEnable(true);
        defaultMQProducer.start();
        for (int i = 0; i < 25; i++) {
            Message message=new Message(topic,"asyn hello".getBytes());
            defaultMQProducer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("获取成功："+sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    System.out.println("发送失败"+e.getMessage());
                    e.printStackTrace();
                }
            });


        }
        Thread.sleep(10000);
        defaultMQProducer.shutdown();

    }
}
