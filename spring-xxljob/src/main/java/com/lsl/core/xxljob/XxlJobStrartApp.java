package com.lsl.core.xxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: liushoulong
 * @Date: 2019/8/20 13:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lsl.xxljob.*"})
@EnableAsync
public class XxlJobStrartApp {

    public static void main(String[] agrs){
        SpringApplication.run(XxlJobStrartApp.class,agrs);
    }
}
