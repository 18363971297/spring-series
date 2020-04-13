package com.lsl;

import java.net.URL;

/**
 * @Author: liushoulong
 * @Date: 2020/1/21 13:33
 * 类加载器的getResouce 默认是从classpath下加载
 */
public class Test {

    public static void main(String[] agrs){
       URL url = Test.class.getResource("");
       System.out.println("" + url.getPath());

       // 返回类的包路径
        String baseName = Test.class.getName();
        // com.lsl.Test
        System.out.println("" + baseName);

    }


    public static String repacleName(String path){

        return "";
    }
}
