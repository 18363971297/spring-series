package com.lsl.core.mybatis.io;

import java.io.InputStream;
import java.net.URL;

/**
 * @Author: liushoulong
 * @Date: 2019/11/15 11:57
 * 加载class资源
 * 通过类加载器进行加载
 */
public class Resource {
    /**初始化容器大小**/
    private static int length = 3;
    /**初始化容器 设置私有不可更改**/
     static ClassLoader[] classLoaders = new ClassLoader[length];

    /**
     * 初始化类加载对象
     */
    static {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader resouceClassLoader = Resource.class.getClassLoader();
        classLoaders[0] = resouceClassLoader;
        classLoaders[1] = currentClassLoader;
        classLoaders[2] = systemClassLoader;
    }

    /**
     *
     */
    public Resource(){

    }
    /**
     *  获取加载器
     * @return  加载器集合
     */
    public static ClassLoader[] getClassLoaders(){
        return classLoaders;
    }

    /**
     *  该方法需要优化，暂时过期不用，便于和下面优化的方法做对比
     *  通过class全路径，加载class对象
     *
     * @param className
     * @return
     */
    @Deprecated
    public static Class<?> classForName(String className) throws ClassNotFoundException{
        try {
            Class<?> cls = Class.forName(className);
            if(cls != null){
                return cls;
            }
        } catch (ClassNotFoundException cnfe) {
          // 忽略该异常，统一有下面处理
        }
        throw new ClassNotFoundException("加载class文件失败，未找到该类文件: " + className);
    }

    /**
     * 使用该方法获取最新的api
     * @param className
     * @return
     */
    public static Class<?> classForName$1(String className) throws ClassNotFoundException{
            for(ClassLoader cl : classLoaders){
                try{
                    Class<?> cs = Class.forName(className,true ,cl );
                    if(cs != null){
                        return cs;
                    }
                } catch (ClassNotFoundException cnfe){
                    // 所有异常不处理，统一由最后处理
                }
            }
            throw new ClassNotFoundException("指定的类路径:" + className + "不存在！");
    }

    /**
     * 通过类加载器加载资源文件：默认从classpath路径下加载
     * @param resource
     * @param classLoaders
     * @return
     */
    public static InputStream getResourceAsStream(String resource,ClassLoader[] classLoaders){
        for (ClassLoader cl : classLoaders) {
            if(cl != null){
                // 通过类加载器的getResourceAsStream加载，默认是从classpath下加载
               InputStream is =  cl.getResourceAsStream(resource);
               if(is == null){
                   // 如果为空，那么就查询资源根目录
                   is = cl.getResourceAsStream("/" +  resource);
               }

               if(is != null){
                   return is;
               }
            }
        }
        return null;
    }

    /**
     * 获取资源文件流
     * @param resource 资源路径
     * @return
     */
    public static InputStream  getResourceAsStream(String resource){
        return getResourceAsStream(resource, getClassLoaders());
    }


    /**
     * 获取资源URL
     * @param resource
     * @param classLoaders
     * @return
     */
    public static URL getResourceAsURL(String resource,ClassLoader[] classLoaders){
        URL url;
        for(ClassLoader cl : classLoaders){
            if(cl != null){
                url = cl.getResource(resource);
                if(url == null){
                    url = cl.getResource("/" + resource);
                }
                if(url != null){
                    return url;
                }
            }
        }
        return null;
    }

    /**
     * 获取资源文件URL，默认加载classpath
     * @param resource
     * @return
     */
    public static URL getResourceAsURL(String resource){
        return getResourceAsURL(resource, getClassLoaders());
    }



    public static void main(String[] agrs){
        // 下面三个类加载器对应的是同一个类加载器sun.misc.Launcher$AppClassLoader
        // 应用类加载器
        InputStream os = getResourceAsStream("resource.properties",getClassLoaders());

        URL url = getResourceAsURL("resource.properties",getClassLoaders());

        if(url != null){

            String protocol = url.getProtocol();
            if("file".equals(protocol)){
                String file = url.getFile();
            }
        }
    }
}
