package com.lsl.core.proxy.dingyi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 15:55
 */
public interface InvocationHandler {

    /**
     * 统一处理
     * @param target
     * @param method
     * @param args
     * @return
     */
    public Object invoke(Object target, Method method,Object[] args)   throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException;
}
