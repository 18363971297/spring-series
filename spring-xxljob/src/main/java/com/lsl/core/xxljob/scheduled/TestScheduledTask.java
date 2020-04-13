package com.lsl.core.xxljob.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: liushoulong
 * @Date: 2019/8/20 13:52
 */
@Component
public class TestScheduledTask {

    @Scheduled(cron = "*/5 * * * * *")
    public void test(){
        System.out.println("1111");
    }

}
