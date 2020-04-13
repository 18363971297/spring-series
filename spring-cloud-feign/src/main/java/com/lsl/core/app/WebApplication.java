package com.lsl.core.app;

import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liushoulong
 * @Date: 2019/9/26 17:38
 */
@SpringBootApplication
@RestController
@EnableFeignClients
public class WebApplication{

   /* @Autowired
    HbaseCdc hbaseCdc;

    @RequestMapping("/")
    public String getName() {
        return hbaseCdc.getObjectByPage();
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
