package com.lsl.core.proxy.dynamicproxy;

import java.lang.reflect.Method;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 20:32
 * 自定义动态代理的handler接口
 */
public interface GPInvocationHandler {

    /**
     *
     * @return
     * @throws Throwable
     */
     public Object invoke(Object proxy,Method method,Object[] args) throws Throwable;
}
