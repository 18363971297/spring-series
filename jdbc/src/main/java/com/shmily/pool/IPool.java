package com.shmily.pool;

import java.sql.Connection;
import java.sql.Driver;

/**
 * @Author: liushoulong
 * @Date: 2019/10/27 11:23
 * 连接池接口
 * 连接池的通用接口
 */
public interface IPool {



    /**
     * 初始化方法
     * 前期准备工作
     */
    public void init();


}
