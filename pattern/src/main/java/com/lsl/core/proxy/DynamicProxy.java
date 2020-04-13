package com.lsl.core.proxy;

import sun.misc.ProxyGenerator;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 15:03
 * 动态代理
 * 可以统一处理n的实例对象的反向代理
 *
 * 统一实现java.lang.reflect.InvocationHandler接口
 */
public class DynamicProxy implements InvocationHandler{


    /**
     * 目标对象
     */
    private Object target;

    /**
     * 获取目标对象的代理对象
     * @param newInstance
     * @return
     */
    public Object getInstance(Object newInstance){
        /**
         * 类加载器：  三种
         * 1 顶级加载器，默认是jdk类加载
         * 2 扩展加载器：ext
         * 3 应用加载器：加载开发中的代码(最多)，在我们直接操作的类中，直接获取到的类加载基本都是应用加载器
         * 4 自定义加载器(很少用)
         */
        this.target = newInstance;
        Class clszz = newInstance.getClass();
        return Proxy.newProxyInstance(clszz.getClassLoader(), clszz.getInterfaces(), this);
    }

    /**
     * 必须实现的接口
     * 即动态代理的类
     * @param proxy  被代理的实例
     * @param method  方法
     * @param args  参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.target,args);
        after();
        return object;
    }

    public void before(){
        System.out.println("代理之前....");
    }

    public void after(){
        System.out.println("代理之后....");
    }


    public static void main(String[] agrs) throws Exception{
        DingPiaoService dingPiaoService =  (DingPiaoService)new DynamicProxy().getInstance(new DingPiaoServiceImp());
        dingPiaoService.ticket("","");

        byte[] b = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{DingPiaoService.class});
        FileOutputStream fo = null;
        try{
            fo = new FileOutputStream("D:\\$Proxy0.class");
            fo.write(b);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        }catch (IOException f){
            f.printStackTrace();
        }finally {
            if(fo != null){
                try{
                    fo.close();
                }catch (IOException i){
                    i.printStackTrace();
                }
            }
        }


    }
}
