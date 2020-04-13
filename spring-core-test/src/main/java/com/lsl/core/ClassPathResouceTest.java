package com.lsl.core;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: liushoulong
 * @Date: 2020/1/20 15:08
 * @desc 获取class指定路径下的资源
 */
public class ClassPathResouceTest {
    private final static String TEST_RESOUCE_PATH_NAME = "/test.properties";

    public static void main(String[] agrs){

        ClassPathResource classPathResource = new ClassPathResource(TEST_RESOUCE_PATH_NAME,ClassPathResouceTest.class);

        try{
            Properties defaultStrategies = PropertiesLoaderUtils.loadProperties(classPathResource);
            String username = defaultStrategies.getProperty("username");
            System.out.println("---" + username);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
