package com.lsl.core.abstracts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: liushoulong
 * @Date: 2019/11/12 22:15
 */
public abstract class AbstractBaseTypeHandler<T> implements ITypeHandler<T> {

    @Override
    public void setParameter(PreparedStatement ps, int i, T paramter) throws SQLException {
        if(paramter == null){
            System.out.println("参数为空");
        }else{
            setNonNullParameter(ps,i,paramter);
        }
    }

    @Override
    public T getResultSet(ResultSet rs, int i) throws SQLException {

        return getResult(rs,i);
    }

    @Override
    public T getResultSet(ResultSet rs, String clumnName) throws SQLException {
        return getResult(rs,clumnName);
    }

    public abstract void setNonNullParameter (PreparedStatement ps, int i, T paramter) throws SQLException;

    public abstract T getResult(ResultSet rs,int i) throws SQLException;

    public abstract T getResult(ResultSet rs,String clumnName) throws SQLException;
}
