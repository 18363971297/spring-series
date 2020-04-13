package com.shmily.version2;

import com.shmily.IMapper;

import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 14:48
 */
public interface IJdbcTemplate<T> {

    /**
     * 保存
     * @param t
     * @throws Exception
     */
    public void save(T t) throws Exception;

    /**
     * 更新
     * @param t
     * @throws Exception
     */
    public void update(T t) throws Exception;

    /**
     * 查询
     * @param params
     * @return List
     * @throws Exception
     */
    public List<T> query(String ...params) throws Exception;

    /**
     * 指定sql和参数，定义返回的结果
     * @param sql
     * @param iMapper
     * @param params
     * @return
     * @throws Exception
     */
    public List<T> query(String sql, IMapper<T> iMapper,Object ...params) throws Exception;

    /**
     *  查询一条数据
     * @param params
     * @return T
     * @throws Exception
     */
    public T selectOne(String ...params) throws Exception;

    /**
     * 删除
     * @param t
     * @throws Exception
     */
    public void delete(T t) throws Exception;

    /**
     * 批量添加
     * @param list
     * @throws Exception
     */
    public void addBatch(List<T> list) throws Exception;

    /**
     * 批量删除
     * @param list
     * @throws Exception
     */
    public void delBatch(List<T> list) throws Exception;
}
