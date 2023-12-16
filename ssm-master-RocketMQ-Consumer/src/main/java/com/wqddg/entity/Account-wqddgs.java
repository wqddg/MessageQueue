package com.wqddg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wqddg
 * @ClassName Account
 * @DateTime: 2023/8/1 19:08
 * @remarks : #
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account implements Serializable {


    private int id;
    private String name;

    private Double money;
}
