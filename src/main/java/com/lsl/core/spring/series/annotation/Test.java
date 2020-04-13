package com.lsl.core.spring.series.annotation;

import com.lsl.spring.series.annotation.config.Config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

/**
 * @Author: liushoulong
 * @Date: 2019/8/30 16:49
 */
public class Test {

    public static void main(String[] agrs){
        boolean isFlag = Config.class.isAnnotationPresent(Configuration.class);
        if(isFlag){
            Configuration configuration = Config.class.getAnnotation(Configuration.class);
            String value = configuration.value();
            if(value != null || value != "" || value.trim() != ""){
                System.out.println(Configuration.class.getName()+":"+value);
            }
        }

        isFlag = Config.class.isAnnotationPresent(ComponentScan.class);
        if(isFlag){
            ComponentScan componentScan = Config.class.getAnnotation(ComponentScan.class);
            String[] backpackages = componentScan.basePackages();
            if (backpackages != null && backpackages.length > 0){
                String name = ComponentScan.class.getName();
                for(int i = 0;i<backpackages.length; i++){
                    System.out.println(name+":"+backpackages[i]);
                }
            }
        }
    }
}
