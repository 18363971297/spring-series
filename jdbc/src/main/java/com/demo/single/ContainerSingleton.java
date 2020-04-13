package com.demo.single;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liushoulong
 * @Date: 2019/10/29 22:12
 */
public class ContainerSingleton {

    // 单例容器，设置私有，防止破坏
    private static Map<String,Object> ioc = new ConcurrentHashMap<>();

    public static Object getBean(String className){
        synchronized (ioc){
            if(!ioc.containsKey(className)){
                Object c = null;
                try{
                    c = Class.forName(className).newInstance();
                    ioc.put(className,c );
                }catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
                    e.printStackTrace();

                }
                return c;
            }else{
                return ioc.get(className);
            }
        }
    }
}
