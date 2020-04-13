package com.shmily;

import java.sql.ResultSet;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 14:42
 */
public interface IMapper<T> {

    public List<T> rowMapper(ResultSet rs) throws Exception;

}
