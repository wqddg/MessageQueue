# MessageQueue
消息队列  java API操作
# 常见的MQ
## ActiveMQ
### 介绍
他是遵循JMS规范的消息队列，有着P2P和pub/sub两种方式。

P2P模式当中：有队列和发送者和接收者。持久化的

pub/sub模式中：有主题，生产者和消费者。非持久化但是可以设置为持久化模式 需要一个指定的clientId和采用指定的消费模式
### 发送原理
发送分为同步发送和异步发送

生产者调用javaAPI send() 然后就会判断producerWindow大小是否为0 如果是阻塞等待ActiveMQ broker当中消息消费完成然后进行释放，判断同步还是异步，
* 同步发送：这里是同步则直接通过传输协议发送到ActiveMQ当中然后阻塞等待ActiveMQ broker中返回消息 结束
* 异步发送：这里是异步则直接将消息添加到producerWindow中，然后等待ActiveMQ broker的使用

### 消费原理
生产者调用receive方法，首先判断unconsumedMessages队列和prefetchSize是否为null 为0 如果为null 为0 则堵塞等待去拉取ActiveMQ中的broker数据。

获取到传输层push数据之后放入队列则接触阻塞，获取消息进行处理，处理完毕放入delivered队列当中随后设置ACK，开始异步返回到方法的返回值和ActiveMQ当中消费完成

### 消息确定ACK原理
在ACT_type当中，有着6个选项。当我们由unconsumedMessages队列取出进行处理的时候，处理完毕之后，如果出现异常者为0 或者3 开始进行重发，当重发的次数到达阈值便进入死信队列，

消费成功则返回2的词 代表消费成功。

消费失败：
* 出现异常
* 事务当中我们没有调用commit方法和rollback方法
* 非事务时，在事务选项中为手动模式 没有调用手动ACK方法


## RabbitMQ
## Kafka
## Rocket MQ
# MQ的作用
## 异步处理
一般情况是串行和并行
假设一个操作 需要100ms的时间 如果有三个操作

并行时间为 200ms
串行时间为 300ms
但是使用MessageQueue就可以为150ms
## 应用解耦
多个功能融合在一起，必须等待一起成功才会成功，否则会失败

在这里通过消息队列，就可以解耦
## 流量削峰
秒杀任务和抢购任务时
## 日志处理
# AMQP简介
AMQP(高级消息队列协议)：进程之间互相传递异步消息的网络协议
## AMQP工作模式
发布者：发送消息到AMQP当中
AMQP：包含交换机和队列 交换机发送到队列当中
接收者：从AMQP接收到消息开始处理
# JMS规范
面对Java API的规范有着一套规范
