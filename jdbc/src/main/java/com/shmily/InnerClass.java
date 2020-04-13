package com.shmily;

/**
 * @Author: liushoulong
 * @Date: 2019/10/27 13:29
 */
public class InnerClass {

    private InnerClass(){
       System.out.println("1111");
    }

    public static InnerClass getInnerClass(){
        return Inner.INNER_CLASS;
    }

    private static class Inner{
        public static final InnerClass INNER_CLASS = new InnerClass();
        private Inner(){
            System.out.println("222");
        }
    }

    public static void main(String[] agrs){
        getInnerClass();
    }


    private static InnerClass instance;
    public static synchronized InnerClass getInstance(){
          if(instance == null){
              instance = new InnerClass();
          }
          return instance;
    }

    public static InnerClass getInstance01(){
        if(instance == null){
            synchronized(InnerClass.class){
                if(instance == null){
                    instance = new InnerClass();
                }
            }
        }
        return instance;
    }
}
