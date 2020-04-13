package com.lsl.core.abstracts;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: liushoulong
 * @Date: 2019/11/12 22:08
 */
public interface ITypeHandler<T> {

    /**
     * 对于要查询的参数进行重置
     * @param ps
     * @param i
     * @param paramter
     * @throws SQLException
     */
    public void setParameter(PreparedStatement ps, int i, T paramter) throws SQLException;

    /**
     * 通过对返回的结构进行重置
     * @param rs
     * @param i   列的索引
     * @return
     * @throws SQLException
     */
    public T getResultSet(ResultSet rs,int i) throws SQLException;


    public T getResultSet(ResultSet rs,String clumnName) throws SQLException;



}
