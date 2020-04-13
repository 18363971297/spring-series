package com.lsl.core.proxy.dynamicproxy;

import com.lsl.proxy.DingPiaoService;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 20:34
 * 1 创建java文件
 * 2 编译class文件
 * 3 实例化代理类
 *
 */
public class GPProxy {

    // 回车换行
    private static String ln = "\r\n";
    /**
     * 获取代理实例
     * @return
     */
   public static Object newProxyInstance(ClassLoader classLoader,Class<?>[] interfaces,GPInvocationHandler h) throws Exception{
       // 生成java的重写文件
       String  src = generateSrc(interfaces);
       String path = GPProxy.class.getResource("").getPath();

       File f = new File(path + "$Proxy0.java");
       FileWriter fw = new FileWriter(f);
       fw.write(src);
       fw.close();
       // 编译java文件生成class文件
       JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
       StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
       Iterable iterable = manager.getJavaFileObjects(f);
       JavaCompiler.CompilationTask task = compiler.getTask(null,manager ,null ,null , null, iterable);
       task.call();
       manager.close();
       // 加载class文件


       // 生成实例
       return null;
   }

    /**
     * 构建java文件
     * @param h
     * @return
     */
   public static String generateSrc(Class<?>[] h){
       // 存储整个的java文件
       StringBuffer clz = new StringBuffer();
       // 定义包
       clz.append("package com.lsl.proxy.dynamicproxy;" + ln);
       // 引入包
       clz.append("import java.lang.reflect.*;" + ln);
       clz.append("import com.lsl.proxy.DingPiaoService;" + ln);
       // 定义类
       clz.append("public class $Proxy0 implements " + h[0].getName() + " {" + ln);
           // 定义变量  定义变量，默认是private修饰
           clz.append("private GPInvocationHandler h;" + ln);
           // 定义构造方法
           clz.append("public $Proxy0(GPInvocationHandler h) {" + ln);
              // 注入参数
              clz.append("this.h = h;" + ln);
           // 构造方法结尾
           clz.append("}" + ln);
           // 定义代理方法
           Method[] methods = h[0].getMethods();
           for (Method m : methods) {
               /**
                *  如何定义一个方法
                *  关键字 返回类型 方法名 参数类型 参数名
                */
               // 方法的参数类型
               StringBuffer paramNames = new StringBuffer();
               // 方法的参数类class
               StringBuffer paramClazzs = new StringBuffer();
               // 获取方法定义的参数的数据类型
               Class<?>[] paramsType = m.getParameterTypes();
               for(int i = 0 ; i < paramsType.length; i++){
                   if(i>0 && i <= paramsType.length -1 ){
                       paramNames.append(",");
                       paramClazzs.append(",");
                   }
                   Class c = paramsType[i];
                   // 变量类型
                   String valType  = c.getName();
                   // 变量名称
                   String valName = "m" + i;

                   paramNames.append(valType + " " + valName);
                   paramClazzs.append(valType + ".class");
               }
               // 定义方法
               clz.append("public " + m.getReturnType().getName() + " " + m.getName() + "(");
                 //方法参数
                 clz.append(paramNames.toString());
               clz.append("){" + ln);
               //添加返回类型

               clz.append("");
               // 方法结尾
               clz.append("}" + ln);
           }

       // 结尾
       clz.append("}");
       return clz.toString();
   }


   public static void main(String[] agrs) throws Exception{
       Proxy01 gp = new Proxy01();
       newProxyInstance(null, new Class[]{SimpleInterface.class},gp);

   }
}
