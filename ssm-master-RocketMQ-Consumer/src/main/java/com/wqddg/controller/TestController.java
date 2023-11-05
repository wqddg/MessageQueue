package com.wqddg.controller;

import com.wqddg.entity.Account;
import com.wqddg.server.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName TestController
 * @DateTime: 2023/8/1 19:20
 * @remarks : #
 */
@Controller
@RequestMapping("account")
public class TestController {


    @Autowired
    private IAccount iAccount;

    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @ResponseBody
    @RequestMapping("findAll")
    public List<Account> findAll(){
        return iAccount.findAll();
    }

}
