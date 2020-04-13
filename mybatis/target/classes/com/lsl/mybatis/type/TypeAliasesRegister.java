package com.lsl.mybatis.type;

import com.lsl.mybatis.io.Resource;

import java.io.*;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @Author: liushoulong
 * @Date: 2019/11/15 10:52
 */
public class TypeAliasesRegister {

    /**
     * 存储指定别名的实体类集合
     * 定义成final，在实例化后，不可别复制修改对象地址
     */
    private final Map<String,Class<?>> typeAliases = new HashMap<>();

    /**
     * 用于初始化预定义的别名类型
     */
    public TypeAliasesRegister(){
        registerAlias("string", String.class);

        registerAlias("byte", Byte.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);

        registerAlias("byte[]", Byte[].class);
        registerAlias("long[]", Long[].class);
        registerAlias("short[]", Short[].class);
        registerAlias("int[]", Integer[].class);
        registerAlias("integer[]", Integer[].class);
        registerAlias("double[]", Double[].class);
        registerAlias("float[]", Float[].class);
        registerAlias("boolean[]", Boolean[].class);

        registerAlias("_byte", byte.class);
        registerAlias("_long", long.class);
        registerAlias("_short", short.class);
        registerAlias("_int", int.class);
        registerAlias("_integer", int.class);
        registerAlias("_double", double.class);
        registerAlias("_float", float.class);
        registerAlias("_boolean", boolean.class);

        registerAlias("_byte[]", byte[].class);
        registerAlias("_long[]", long[].class);
        registerAlias("_short[]", short[].class);
        registerAlias("_int[]", int[].class);
        registerAlias("_integer[]", int[].class);
        registerAlias("_double[]", double[].class);
        registerAlias("_float[]", float[].class);
        registerAlias("_boolean[]", boolean[].class);

        registerAlias("date", Date.class);
        registerAlias("decimal", BigDecimal.class);
        registerAlias("bigdecimal", BigDecimal.class);
        registerAlias("biginteger", BigInteger.class);
        registerAlias("object", Object.class);

        registerAlias("date[]", Date[].class);
        registerAlias("decimal[]", BigDecimal[].class);
        registerAlias("bigdecimal[]", BigDecimal[].class);
        registerAlias("biginteger[]", BigInteger[].class);
        registerAlias("object[]", Object[].class);

        registerAlias("map", Map.class);
        registerAlias("hashmap", HashMap.class);
        registerAlias("list", List.class);
        registerAlias("arraylist", ArrayList.class);
        registerAlias("collection", Collection.class);
        registerAlias("iterator", Iterator.class);

        registerAlias("ResultSet", ResultSet.class);
    }

    /***获取注册别名**/
    public Map<String,Class<?>> getTypeAliases(){
        return typeAliases;
    }


    public Class<?> getTypaAliases(String alias){
        return typeAliases.get(alias);
    }

    /***************注册方法******************/

    /**
     *
     * @param value 指定class文件，截取类名并首字母小写进行注册
     */
    public void registerAliase(Class<?> value){
       if(value == null){
           throw new NullPointerException("mybatis 别名注册异常：传入空指针数据");
       }
       // 得到类的简单名称，即首字符小写的类名
       String alias = value.getSimpleName();
       // 该位置可以判断是否使用了注解别名，如果存在，那么就获取注册定义的别名
        // 定义注解类
        Alias annoAlias = value.getAnnotation(Alias.class);
        if(annoAlias != null){
            alias = annoAlias.value();
        }
        registerAlias(alias,value);
    }




    /**
     * 注册别名
     * @param alias 指定的别名
     * @param value  别名对应的类文件  全局唯一
     */
    public void registerAlias(String alias,Class<?> value){
        if(alias == null){
            throw new RuntimeException("别名参数不能为空");
        }
        // 不可重复存在
        // 统一别名的不同值，则抛出异常
        if(typeAliases.containsKey(alias) && typeAliases.get(alias) != null && !typeAliases.get(alias).equals(value)){
            throw new RuntimeException("自定义别名以存在"+ alias +"对应类："+ typeAliases.get(alias).getName() +" ,冲突类：" + value.getName());
        }
        typeAliases.put(alias,value);
    }

    /***
     *  通过类路径注册
     *  默认别名使用该类的类名，首字母小写
     *  classpath: 指定类的全路径名称 com.lsl.x.x.x
     * */
    public void registerAlias(String alias,String classpath){
        try{
            registerAlias(alias, Resource.classForName$1(classpath));
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }

    /**
     * 指定包名，然后对包下的所有class进行注册
     */
    public void registerAlias(String packagename){
       // 处理包名
        registerAlias(Object.class, packagename);
    }

    /**
     * 指定类型
     * @param type  指定查找包下指定实例的
     * @param packagename
     */
    public void registerAlias(Class<?> type,String packagename){
        // 包名处理，'.' 到'/'
        if(packagename != null && packagename != ""){
            packagename = packagename.replace(".", "/");
        }
        list(packagename);
        // 加载数据
        //list(packagename);
        // 得到包下的所有资源文件
        // 过滤class文件
        // 加载class文件
        //

        // 查找到所有的子孩子，然后存入
        // registerAliase(, );
    }

    public void list(String packagename){
        // 包资源数据
        try{
            Enumeration<URL> enuUrls = Thread.currentThread().getContextClassLoader().getResources(packagename);
            List<URL> urls = Collections.list(enuUrls);
            // class .别名使用简单类名，
            for(URL url : urls){
               List<String> strClass = list(url, packagename);
               for(String cl:strClass){
                   // 类加载器加载
                   cl = cl.replace("/", ".");
                   if(!cl.endsWith(".class")){
                       continue;
                   }
                   int index = cl.lastIndexOf(".");
                   cl = cl.substring(0,index);
                  Class c =  getClass().getClassLoader().loadClass(cl);

                  if(c == null){
                      throw new ClassNotFoundException("指定类[ " + cl + " ] 定义别名，抛出异常，该类不存在");
                  }

                  if(c.isMemberClass() || c.isInterface() || c.isAnonymousClass() ||  Modifier.isAbstract(c.getModifiers())){
                    continue;
                  }
                  String alias = c.getSimpleName();
                   registerAlias(alias,c );
               }
            }
        } catch (IOException io){
            io.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }

    /**
     *
     * @param url  需要加载的url
     * @param packagename  当前资源的路径包
     * @return
     */
    public List<String> list(URL url,String packagename) {
        // 追加的class文件
        List<String> list = new ArrayList<>();
        try{
                // 遍历加载数据，查找包下的资源，并添加到集合
                InputStream is = url.openStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                // line 读取的每一行数据
                List<String> children = new ArrayList<>();
                for(String line;(line = br.readLine()) != null ;){
                    children.add(line);
                    URL u = Thread.currentThread().getContextClassLoader().getResource(packagename + "/" + line);
                    // 如果资源不存在那么就清空子
                    // 防止资源类被篡改，或者删除等
                    if(u == null){
                        children.clear();
                        break;
                    }

                }
                // 获取当前url的协议路径
                String prefix = url.toExternalForm();
                if(!prefix.endsWith("/")){
                    prefix = prefix + "/";
                }
                // 对children 子进行集合处理
                for(String s : children){
                    // 当前子
                    String resoucePath = packagename + "/" +  s;
                    list.add(resoucePath);
                    URL chr = new URL(prefix + s);
                    // 递归处理
                    list.addAll(list(chr, resoucePath));
                }
        } catch (IOException io){
             io.printStackTrace();
        }
        return list;
    }


    public static void main(String[] agrs){
        TypeAliasesRegister t = new TypeAliasesRegister();
        t.registerAlias(Object.class,"com.lsl");
        Map<String,Class<?>> mps = t.getTypeAliases();
        Set<Map.Entry<String,Class<?>>> set = mps.entrySet();
        Iterator<Map.Entry<String,Class<?>>> entrys = set.iterator();
        while(entrys.hasNext()){
            Map.Entry c = entrys.next();
            System.out.println("" + c.getKey());
        }
    }
}
