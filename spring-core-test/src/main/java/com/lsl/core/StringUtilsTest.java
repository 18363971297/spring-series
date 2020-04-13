package com.lsl.core;

import org.springframework.util.StringUtils;

/**
 * @Author: liushoulong
 * @Date: 2020/1/20 15:17
 */
public class StringUtilsTest {

    public static void main(String[] agrs){
        String path = StringUtils.cleanPath("/test.properties");
        System.out.println("--" + path);
    }
}
