package com.lsl.core.app;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: liushoulong
 * @Date: 2019/9/26 18:04
 */
@FeignClient(name = "name",url = "116.228.77.185:21900",path = "/cdcapi/hbase/getObjectByPage")
public interface HbaseCdc {
    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json","Inner_token","PXH0dP5I8qQ8UbFPpzm67cQkm7j8tWT2Kwn6J6SXYkfp2kMo/lSqHQ=="})
    @RequestMapping(value = "",method = RequestMethod.POST)
    public String getObjectByPage();
}


