package com.lsl.core.proxy;

import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 15:01
 * 静态代理
 */
public class ProxyMain {

    public static void main(String[] agrs) throws Throwable{
       /*DingPiaoProxy dingPiaoProxy = new DingPiaoProxy();

       dingPiaoProxy.ticketProxy();
       int a = DingPiaoProxy.class.hashCode();
        int b = ProxyMain.class.hashCode();
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        String s1 = "111";
        String s2 = "111";
        String s3 = "111";
        String s4 = "111";

        System.out.println("" + s1.hashCode());
        System.out.println("" + s2.hashCode());
        System.out.println("" + s3.hashCode());
        System.out.println("" + s4.hashCode());
        System.out.println(""+ hash(s4));

        System.out.println("n " + tableSizeFor(10));*/

        classForname();
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }


    static void classForname() throws Throwable{
        // 获取所有的方法
        Class cls =  Class.forName("com.lsl.proxy.DingPiaoServiceImp");
        Object obj = cls.newInstance();
        // 接口集合
        Class[] interfaces = cls.getInterfaces();
        // 判断需要的类型是否存在
        /**
         * c.getTypeName()  是整个类加报名的路径全称
         * 可以获取当前类型的全称，然后可以根据全称进行了类加载
         * String.class.getName()
         *     returns "java.lang.String"
         */
        for(Class c : interfaces){
            if(c.getTypeName().equals("com.lsl.proxy.DingPiaoService")){
               // 存在该接口
                Method[] methods = c.getMethods();
                StringBuffer paramsMethod = new StringBuffer();
                // 方法类型
                for(Method m:methods){
                    // 方法的定义类型，方法的返回类型  方法的名称
                    int mod = m.getModifiers();
                    System.out.println("修饰符" + Modifier.toString(mod));
                    Class returnType = m.getReturnType(); // 返回类型
                    System.out.println("返回类型：" + returnType.getName());
                    String methodName = m.getName(); // 方法名称
                    System.out.println("方法名称: " + methodName + "  实例:" + m.hashCode());
                    Class[] paramTypes = m.getParameterTypes(); // 参数类型
                    for (int i=0;i<paramTypes.length;i++){
                        System.out.println("参数"+i+": " + paramTypes[i].getName());
                    }
                    Parameter[] params = m.getParameters();  // 参数
                    for(Parameter p:params){
                        System.out.println("参数名:"+p.getName());
                    }
                    System.out.println("------------");
                }
            }
        }

        Method m = cls.getMethod("ticket", new Class[]{String.class, String.class});
        Method m1 = cls.getMethod("ticket", new Class[]{String.class, String.class});
        System.out.println("方法名称:" + m.getName() + "  实例:" + m.hashCode());
        System.out.println("方法名称1:" + m1.getName() + "  实例1:" + m1.hashCode());
        Integer ret =  (Integer)m.invoke(obj,new String[]{"1","1"});
        System.out.println("返回结果:" + ret);
        invoke(obj,m,new String[]{"1","1"});
        /**
         *  public 返回值  方法名(参数类型 变量,参数类型 变量){
         *
         *  }
         */
    }


    public static void invoke(Object proxy,Method m,Object[] args){

    }

}
/**
 * 1  每个类(类，接口，枚举类) 的class字节码文件都是唯一的
 * 2  每个字节码class生成的Class对象类也是唯一的，即一个字节码文件a.class 如论被加载多少次，得到的都是同一个Class实例
 * 3  每个Class实例中，存在多个Filed和method，每个属性对象、方法对象在内存中都是唯一存在的，所以在获取方法对象时
 * 多次获取得到的是同一个方法对象
 *
 * ***/