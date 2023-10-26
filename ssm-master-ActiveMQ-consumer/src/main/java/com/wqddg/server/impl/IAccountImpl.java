package com.wqddg.server.impl;

import com.wqddg.dao.AccountDao;
import com.wqddg.entity.Account;
import com.wqddg.server.IAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName IAccountImpl
 * @DateTime: 2023/8/1 19:07
 * @remarks : #
 */
@Service
public class IAccountImpl implements IAccount {

    @Autowired
    private AccountDao accountDao;
    @Override
    public List<Account> findAll() {
        List<Account> all = accountDao.findAll();
        return all;
    }
}
