package com.lsl.core.spring.series.annotation.scheduled_task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: liushoulong
 * @Date: 2019/8/19 16:26
 */

@Component
public class TestScheduledTask {


    @Scheduled(cron = "0 * * * * *")
    public  void test(){
        System.out.println("--------");
    }
}
