package com.lsl.core.spring.series.context;

import com.lsl.core.spring.series.common.model.User;
import com.lsl.spring.series.common.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Author: liushoulong
 * @Date: 2019/8/13 11:21
 */
public class BootStratFileSysXmlAppContext {
    public static void main(String[] agrs){

        ApplicationContext fileApplicationContext = new FileSystemXmlApplicationContext("classpath:application.xml");
        User user = fileApplicationContext.getBean(User.class);
        System.out.println(""+user.getUsername());
    }
}
