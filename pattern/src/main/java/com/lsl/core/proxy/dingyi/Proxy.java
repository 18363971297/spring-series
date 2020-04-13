package com.lsl.core.proxy.dingyi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 15:57
 * 动态代理实现
 */
public class Proxy {

    protected   InvocationHandler  h;

    public Proxy(InvocationHandler h){
        this.h = h;
    }
}
