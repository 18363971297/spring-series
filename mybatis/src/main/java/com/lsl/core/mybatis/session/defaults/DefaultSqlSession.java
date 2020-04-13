package com.lsl.core.mybatis.session.defaults;

import com.lsl.mybatis.session.SqlSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/11/14 14:10
 */
public class DefaultSqlSession implements SqlSession {

    /**
     *
     * @param statement  声明的sql
     * @param <T>
     * @return
     */
    @Override
    public <T> T selectOne(String statement) {
        return this.selectOne(statement,null );
    }

    /**
     *
     * @param statement  sql声明
     * @param params  预定义参数
     * @param <T>
     * @return
     */
    @Override
    public <T> T selectOne(String statement, Object params) {
        /**
         *  1 查找到存入的map
         */
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement) throws SQLException {
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement, Object params) throws SQLException {
        return null;
    }

    @Override
    public int insert(String statement) {
        return 0;
    }

    @Override
    public int insert(String statement, Object params) {
        return 0;
    }

    @Override
    public int update(String statement) {
        return 0;
    }

    @Override
    public int update(String statement, Object params) {
        return 0;
    }

    @Override
    public int delete(String statement) {
        return 0;
    }

    @Override
    public int delete(String statement, Object params) {
        return 0;
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
