package com.wqddg.server;

import com.wqddg.entity.Account;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName IAcount
 * @DateTime: 2023/8/1 19:07
 * @remarks : #
 */
public interface IAccount {

    List<Account> findAll();

}
