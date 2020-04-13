package com.test.kafka.controller;

import com.test.kafka.template.KafkaTemplateFacotry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liushoulong
 * @Date: 2020/4/9 12:52
 */
@Controller
@RequestMapping("/produer")
public class ProductSendController {


    @RequestMapping("/send/{count}")
    public Map<String,Object> send(@PathVariable int count){
        Map<String,Object> result = new HashMap<>();

        for(int i=0;i<count;i++){
            KafkaTemplateFacotry.send("test-handler-topic", i+"");
        }
        result.put("message", "发送成功");
        return result;
    }
}
