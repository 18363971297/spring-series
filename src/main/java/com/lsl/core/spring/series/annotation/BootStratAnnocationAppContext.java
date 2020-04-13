package com.lsl.core.spring.series.annotation;

import com.lsl.spring.series.annotation.config.Config;
import com.lsl.spring.series.common.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.config.ScheduledTask;

/**
 * @Author: liushoulong
 * @Date: 2019/8/19 11:59
 */
public class BootStratAnnocationAppContext {
    public static void main(String[] agrs){

        /**
         * 注解是xml的替代
         * 1  避免臃肿的xml维护
         * 2  解耦性强
         * 3
         */
        AnnotationConfigApplicationContext annotationApplicationContext = new AnnotationConfigApplicationContext();
        annotationApplicationContext.register(Config.class);
        annotationApplicationContext.refresh();
        User user = annotationApplicationContext.getBean(User.class);

        System.out.println(""+user.toString());



    }
}
