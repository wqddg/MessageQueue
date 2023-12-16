package com.rocketMQ.oneTest;

/**
 * @Author: wqddg
 * @ClassName Order
 * @DateTime: 2023/10/31 11:54
 * @remarks : #
 */
public class Order {
    public Order() {
    }

    public Order(long orderId, String desc) {
        this.orderId = orderId;
        this.desc = desc;
    }

    private long orderId;

    private String desc;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }
}
