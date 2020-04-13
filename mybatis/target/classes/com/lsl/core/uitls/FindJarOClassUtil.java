package com.lsl.core.uitls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: liushoulong
 * @Date: 2019/11/22 11:57
 */
public class FindJarOClassUtil {


    /**
     * 1  根据包名查找包下的所有资源文件
     */

    public static void main(String[] agrs){
       String path = "com.lsl.mybatis.io";
       path = path.replace(".", "/");
       // 这个已经默认了使用路径，即当前路径，根据当前class类名进行获取
       Thread.currentThread().getContextClassLoader().getResourceAsStream("");

       String clPath = FindJarOClassUtil.class.getName();
       System.out.println("" + clPath);

       // 获取包，如何得到整个路径
       // 可以使用getResources,然后得到路径
       URL url =  Thread.currentThread().getContextClassLoader().getResource(path);
       System.out.println("" + url.getPath());
       System.out.println("" + url.toExternalForm());
       System.out.println("" + url.getProtocol());
       try{
           URL u = new URL("/D:/DevelopEnv/git_code/demo/spring-series/mybatis/target/classes/com/lsl/mybatis/io");
       }catch (MalformedURLException i){
           i.printStackTrace();
       }
       /*try{
           InputStream is = url.openStream();
           BufferedReader br = new BufferedReader(new InputStreamReader(is));
           for(String line;(line = br.readLine()) !=null;){
               System.out.println(""+ line);
               // 获取到了该目录下的资源文件
               if(line.endsWith(".java")){
                   URL singlePath = Thread.currentThread().getContextClassLoader().getResource(path + "/" + line);
                   InputStream oo = singlePath.openStream();
                   BufferedReader sbr = new BufferedReader(new InputStreamReader(oo));
                   for(String sline;(sline = sbr.readLine()) != null;){
                       System.out.println("" + sline);
                   }
               }
           }
       } catch (IOException io){
           io.printStackTrace();
       }*/
    }
}
