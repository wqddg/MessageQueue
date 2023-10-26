package com.wqddg;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName wqddg
 * @DateTime: 2023/10/25 12:11
 * @remarks : #
 */
public class wqddg {
    public static void main(String[] args) {
        String abs="hello,word";
        String[] split = abs.split(",");
        List<String> list = Arrays.asList(split);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println();
    }
}
