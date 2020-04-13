package com.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author: liushoulong
 * @Date: 2019/10/22 14:33
 * 文件上传
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 上传
     * @return
     */
    @PostMapping(value = "/upload")
    public Map<String,Object> upload(CommonsFileUploadSupport dd){

        Map<String,Object> result = new HashMap<>();

        return result;
    }

    @GetMapping("/test")
    public Map<String,Object> test(){
        Map<String,Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "成功");
        return result;
    }

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    @GetMapping("/requestMappingHandlerMapping")
    @ResponseBody
    public Map<String,Object> requestMappingHandlerMapping(){
        Map<String,Object> result = new HashMap<>();
        Map<RequestMappingInfo,HandlerMethod> mappings = requestMappingHandlerMapping.getHandlerMethods();

        /*Set<RequestMappingInfo> requestMappingInfos =  mappings.keySet();
        Iterator<RequestMappingInfo> iterators = requestMappingInfos.iterator();
        while (iterators.hasNext()){
            RequestMappingInfo rmi = iterators.next();
            System.out.println("" + rmi.toString());
        }*/

        result.put("success", true);
        result.put("message", mappings);
        return result;
    }
}
