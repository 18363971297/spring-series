package com.entry;

/**
 * @Author: liushoulong
 * @Date: 2019/9/26 14:05
 */
public class Main {

    public static void main(String[] agrs){
        weiYunSuan();

        System.out.println(""+('*'*10));
    }

    /**
     * 位运算操作
     */
    public static void weiYunSuan(){
        // & 与运算
        /**
         *  0 0 0 0 0 1 0 1
         *  0 0 0 0 0 1 1 0
         *  -----------------
         *  0 0 0 0 0 1 0 0
         */
        int b = 5 & 6;
        // 输出结果：4
        System.out.println("b:"+b);

        // | 或运算
        /**
         *  0 0 0 0 0 1 0 1
         *  0 0 0 0 0 1 1 0
         *  -----------------
         *  0 0 0 0 0 1 1 1
         */
        int c = 5 | 6;
        // 输出结果：7
        System.out.println("c:"+c);

        // 异或运算
        /**
         *  0 0 0 0 0 1 0 1
         *  0 0 0 0 0 1 1 0
         *  -----------------
         *  0 0 0 0 0 0 1 1
         */
        int a = 5 ^ 6;
        System.out.println("a : " +a);
        // 输出为3

        // 取反
        // 所有取反整数 = 当前值的相反值 减1
        int d = ~5;
        // -5-1
        System.out.println("d:"+d);

        //左位移
        /**
         *  0 1 0 1 向左平移1位，地位补零
         *  1 0 1 0 被左移后的结果：10
         */
        int e = 5<<1;
        // 输入结果：10
        System.out.println("e:"+e);

        // 右位移
        /**
         *  0 1 0 1 向右移动一位，高位补零
         *  0 0 1 0 被右移后的结果：2
         */
        int f = 5 >> 1;
        // 输出结果: 2
        System.out.println("f:"+f);
    }
}
