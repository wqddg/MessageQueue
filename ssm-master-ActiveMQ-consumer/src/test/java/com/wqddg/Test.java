package com.wqddg;

import com.wqddg.entity.Account;
import com.wqddg.server.IAccount;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName Test
 * @DateTime: 2023/8/1 19:11
 * @remarks : #
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Test {


    @Autowired
    private IAccount iAccount;

    @org.junit.Test
    public void test_1(){
        iAccount.findAll().stream().forEach(System.out::println);
    }


    @org.junit.Test
    public void test_2(){
    }
}
