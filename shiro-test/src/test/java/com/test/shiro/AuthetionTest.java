package com.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liushoulong
 * @Date: 2020/3/11 7:19
 */
public class AuthetionTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthetionTest.class);

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("lsl", "123");
        simpleAccountRealm.addAccount("zhangsan", "123");
    }

    @Test
    public void login(){
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lsl","123");

        // 由主体进行认证提交
        subject.login(token);

        // 该主体是否认证通过
        boolean flag = subject.isAuthenticated();
        System.out.println("是否认证:" + flag );
    }
}
