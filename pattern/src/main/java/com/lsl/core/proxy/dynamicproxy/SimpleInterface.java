package com.lsl.core.proxy.dynamicproxy;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 22:47
 */
public interface SimpleInterface {

    public void success(String name,String age);

    public void fail(String message);

    public void error(String message);
}
