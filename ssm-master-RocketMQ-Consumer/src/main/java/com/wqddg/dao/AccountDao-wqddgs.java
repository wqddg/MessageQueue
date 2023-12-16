package com.wqddg.dao;

import com.wqddg.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName AccountDao
 * @DateTime: 2023/8/1 19:56
 * @remarks : #
 */
@Repository
public interface AccountDao {

    List<Account> findAll();



}
