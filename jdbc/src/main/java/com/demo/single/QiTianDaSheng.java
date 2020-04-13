package com.demo.single;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/29 22:48
 *
 * 进行深拷贝
 */
public class QiTianDaSheng extends Make implements Serializable{

    private JinGuBang jinGuBang;
    public QiTianDaSheng(){
           this.birth = new Date();
           jinGuBang = new JinGuBang();
    }

    /**
     * 深拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return depthClone();
    }


    private Object depthClone(){
        QiTianDaSheng sheng = null;
        try{

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            sheng = (QiTianDaSheng)objectInputStream.readObject();

        } catch (Exception i){
            i.printStackTrace();
        }
        return sheng;
    }


    public static void main(String[] agrs){
       QiTianDaSheng sheng = new QiTianDaSheng();
       try{
           QiTianDaSheng s = (QiTianDaSheng)sheng.clone();

           System.out.println("== " + (s.jinGuBang == sheng.jinGuBang));
       }catch (Exception e){
          e.printStackTrace();
       }


        ArrayList<JinGuBang> a = new ArrayList<>();
       JinGuBang jinGuBang = new JinGuBang();
       JinGuBang jinGuBang1 = new JinGuBang();
       JinGuBang jinGuBang2 = new JinGuBang();
       JinGuBang jinGuBang3 = new JinGuBang();
       a.add(jinGuBang);
       a.add(jinGuBang1);
       a.add(jinGuBang2);
       a.add(jinGuBang3);

       ArrayList<JinGuBang> b = (ArrayList<JinGuBang>)a.clone();

       System.out.println("a = b" + (a == b));

       System.out.println("0==0" +(jinGuBang == b.get(0)));
       System.out.println("1==1" +(jinGuBang1 == b.get(1)));
       System.out.println("2==2" +(jinGuBang2 == b.get(2)));
       System.out.println("3==3" +(jinGuBang3 == b.get(3)));



    }
}
