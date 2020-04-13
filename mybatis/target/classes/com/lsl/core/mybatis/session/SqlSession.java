package com.lsl.core.mybatis.session;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/11/14 11:46
 * 定义数据库操作的回话接口
 * 该接口是一个不同类型重用的接口，所以方法需要做成泛型
 * 回话接口是需要被关闭的，需要继承关闭接口
 */
public interface SqlSession extends Closeable {
    /**
     * 增删改查
     */


    /**
     * 查询一条数据
     * @param statement  声明的sql
     * @return T 返回的对象
     */
    public <T> T selectOne(String statement);

    /**
     *  查询单条数据
     * @param statement  sql声明
     * @param params  预定义参数
     * @return T
     */
    public <T> T selectOne(String statement,Object params);

    /**
     * 查询list集合
     * @param statement
     * @param <E>
     * @return
     * @throws SQLException
     */
    public <E> List<E> selectList(String statement) throws SQLException;

    /**
     * 查询list集合
     * @param statement
     * @param params
     * @param <E>
     * @return
     * @throws SQLException
     */
    public <E> List<E> selectList(String statement,Object params) throws SQLException;


    public int insert(String statement);

    public int insert(String statement,Object params);

    public int update(String statement);

    public int update(String statement,Object params);

    public int delete(String statement);

    public int delete(String statement,Object params);

    /**
     * 当前回话的连接
     * @return
     */
    Connection getConnection();


}
