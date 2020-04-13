package com.demo.single;

import java.io.Serializable;

/**
 * @Author: liushoulong
 * @Date: 2019/10/29 22:40
 */
public class CloneSingle implements Cloneable ,Serializable{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
