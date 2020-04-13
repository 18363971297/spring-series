package com.lsl.core.abstracts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: liushoulong
 * @Date: 2019/11/12 22:25
 */
public class StringBaseTypeHandler extends AbstractBaseTypeHandler<String> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String paramter) throws SQLException {
       // ps.setString(i, paramter);
        System.out.println("参数设置成功");
    }

    @Override
    public String getResult(ResultSet rs, int i) throws SQLException {
        if(rs == null){
            System.out.println("获取成功1");
            return "";
        }
        return rs.getString(i);
    }

    @Override
    public String getResult(ResultSet rs, String clumnName) throws SQLException {
        if(rs == null){
            System.out.println("获取成功2");
            return "";
        }
        return rs.getString(clumnName);
    }


    public static void main(String[] agrs) throws Throwable{
        StringBaseTypeHandler sbh = new StringBaseTypeHandler();

        sbh.setParameter(null,1 , "");
        sbh.getResultSet(null,1 );

    }
}
