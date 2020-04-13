package com.lsl.core.proxy;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 14:50
 */
public class DingPiaoServiceImp implements DingPiaoService {


    @Override
    public int ticket(String name,String age) {
        System.out.println("ticket - name:" + name);
        System.out.println("ticket - age:" + age);
        System.out.println("ticket - 购票成功");
        return 0;
    }

    @Override
    public int refesult(int a) {
        System.out.println("退票成功");
        return 0;
    }
}
