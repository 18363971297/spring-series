package com.lsl.core.spring.series.context;

import com.lsl.core.spring.series.common.model.User;
import com.lsl.spring.series.common.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: liushoulong
 * @Date: 2019/8/13 11:20
 */
public class BootStratClassPathXmlAppContext {

    public static void main(String[] agrs){
        ApplicationContext ac = new ClassPathXmlApplicationContext("application.xml");
        User user = ac.getBean("user",User.class);
        System.out.println("" + user.getUsername());
    }
}
