package com.lsl.core.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 14:47
 *  定义接口，是用于服务特定的服务
 *  一个接口被定义，应该只有一个职责，那就是服务于专项
 *  比如购票，那么他不能和商品混淆
 */
public interface DingPiaoService {

    /**
     * 购票
     * @return
     */
    public int  ticket(String name,String age) throws Exception;

    /**
     * 退票
     * @return
     */
    public int refesult(int a) throws ClassNotFoundException;

    /*public static void main(String[] agrs){
        Method[] methods =
                DingPiaoService.class.getMethods();
        for(Method m : methods){
            String name = m.getName();
            // 该方法所属的类
            Class decClass = m.getDeclaringClass();
            Class[] exceptionTypes = m.getExceptionTypes();
            Class[] paramsType = m.getParameterTypes();
            Class returnType = m.getReturnType();
            Parameter[] parameters = m.getParameters();

            System.out.println("类名:"+name);
            System.out.println("定义类：" + decClass);
            System.out.println("异常类型："+ exceptionTypes);
            System.out.println("参数类型：" + paramsType);
            System.out.println("返回类型：" + returnType);

        }
    }*/
}
