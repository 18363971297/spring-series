package com.shmily.version2;

import com.shmily.IMapper;
import com.shmily.utils.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 14:48
 */
public class JdbcTemplate<T> implements IJdbcTemplate<T>{

    @Override
    public void save(T o) throws Exception {

    }

    @Override
    public void update(T o) throws Exception {

    }

    @Override
    public List<T> query(String... params) throws Exception {

        String[] pas = params;
        return null;
    }

    @Override
    public T selectOne(String... params) throws Exception {
        return null;
    }

    @Override
    public void delete(T o) throws Exception {

    }

    @Override
    public void addBatch(List<T> list) throws Exception {

    }

    @Override
    public void delBatch(List<T> list) throws Exception {

    }

    /**
     * @param sql
     * @param iMapper
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<T> query(String sql, IMapper<T> iMapper, Object... params) throws Exception {
        JDBCUtils.getConnection();
        PreparedStatement ps = JDBCUtils.getPrepareStatement(sql);
        Object[] pas = params;
        for(int i=0 ; i<pas.length;i++){
            ps.setObject(i+1, pas[i]);
        }
        ResultSet rs = ps.executeQuery();
        return iMapper.rowMapper(rs);
    }
}
