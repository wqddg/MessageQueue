package com.rocketMQ.oneTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName UtilsList
 * @DateTime: 2023/10/31 11:55
 * @remarks : #
 */
public class UtilsList {
    public static List<Order> orderList(){
        List<Order> orders=new ArrayList<>();
        Order order1=new Order(001,"创建");
        orders.add(order1);
        order1=new Order(002,"创建");
        orders.add(order1);
        order1=new Order(001,"付款");
        orders.add(order1);
        order1=new Order(003,"创建");
        orders.add(order1);
        order1=new Order(002,"付款");
        orders.add(order1);
        order1=new Order(003,"付款");
        orders.add(order1);
        order1=new Order(003,"推送");
        orders.add(order1);
        order1=new Order(002,"推送");
        orders.add(order1);
        order1=new Order(001,"推送");
        orders.add(order1);
        order1=new Order(001,"完成");
        orders.add(order1);
        order1=new Order(002,"完成");
        orders.add(order1);
        order1=new Order(003,"完成");
        orders.add(order1);
        return orders;
    }
}
