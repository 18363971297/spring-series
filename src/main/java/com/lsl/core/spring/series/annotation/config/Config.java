package com.lsl.core.spring.series.annotation.config;

import com.lsl.spring.series.common.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liushoulong
 * @Date: 2019/8/19 13:45
 */
@Configuration
@ComponentScan(basePackages = {"com.lsl.spring.series.annotation.*"} )
public class Config {

    @Bean
    public User user(){
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        user.setSex("nv");
        user.setAge(10);
        return user;
    }


}
