package com.lsl.core.mybatis.io;

import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: liushoulong
 * @Date: 2019/11/20 17:05
 *
 * 解析工具
 * 作用：查找包下的所有class文件
 */
public class ResolverUitls {

    public static void main(String[] agrs){
       /*
        InputStream is = ResolverUitls.class.getResourceAsStream("resources.properties");
        Properties p = new Properties();
        try{
            p.load(is);
            String username = p.getProperty("username");
            System.out.println("" +  username);
        } catch (IOException io){

        }*/

        /*InputStream ii = ResolverUitls.class.getResourceAsStream("resources.properties");
        Properties properties = new Properties();
        try{
            properties.load(ii);
        }catch (IOException io){
            io.printStackTrace();
        }

        //
        File file = new File("");*/
        //FileWriter fileWriter = new FileWriter();

        /*URL url = ResolverUitls.class.getResource("/com/lsl");
        InputStream is = ResolverUitls.class.getResourceAsStream("/com/lsl");
        URL r = ResolverUitls.class.getClassLoader().getResource("/com/lsl");
        InputStream i = ResolverUitls.class.getClassLoader().getResourceAsStream("/com/lsl");

        System.out.println("" + url.getPath());


        Boolean a = Object.class.isAssignableFrom(ResolverUitls.class);

        Boolean b = DefaultVFS.class.isAssignableFrom(DefaultVFS.class);
        System.out.println("" + a);
        System.out.println("" + b);*/

        ResolverUitls.A a = new ResolverUitls.A();
        System.out.println("" + a.getClass().getName());
        System.out.println("" + a.getClass().isMemberClass());

        ResolverUitls b = new ResolverUitls();
        String name = b.getClass().getName();
        System.out.println("" + name);
        System.out.println("" + b.getClass().isMemberClass());

    }

    static class A{

    }
}
