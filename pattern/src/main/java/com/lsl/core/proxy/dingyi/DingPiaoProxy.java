package com.lsl.core.proxy.dingyi;


import com.lsl.core.proxy.DingPiaoService;
import com.lsl.proxy.DingPiaoService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @Author: liushoulong
 * @Date: 2019/11/3 16:03
 */
public class DingPiaoProxy extends Proxy implements DingPiaoService {

    private static Method ticket;
    static{
        try{
            ticket = Class.forName("com.lsl.proxy.DingPiaoService").getMethod("ticket",new Class[0]);
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        } catch (NoSuchMethodException noSuchMethod){
            noSuchMethod.printStackTrace();
        }
    }

    public DingPiaoProxy(InvocationHandler h){
        super(h);
    }

    @Override
    public final int ticket(String name,String age) {
        try{
           return ((Integer)this.h.invoke(this,ticket ,null )).intValue();
        }catch (IllegalAccessException e){
            throw new IllegalArgumentException(e);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e);
        }catch (Throwable it){
            throw new UndeclaredThrowableException(it);
        }
    }

    @Override
    public int refesult(int a) {
        return 0;
    }
}
