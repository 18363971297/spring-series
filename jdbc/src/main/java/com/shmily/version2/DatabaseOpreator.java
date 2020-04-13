package com.shmily.version2;

import com.shmily.IMapper;
import com.shmily.model.Greet;
import com.shmily.utils.JDBCUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 11:41
 * 对上一版进行升级改造
 */
public class DatabaseOpreator {

    public static void main(String[] agrs){
       JdbcTemplate<Greet> jdbcTemplate = new JdbcTemplate<>();
       try{
           List<Greet> list = jdbcTemplate.query("select * from greet where id = ?", new IMapper<Greet>() {
               @Override
               public List<Greet> rowMapper(ResultSet rs) throws Exception {
                   List<Greet> result = new ArrayList<>();
                   if(rs != null){
                       while (rs.next()) {
                           Greet greet = new Greet();
                           greet.setName(rs.getString("name"));
                           greet.setId(rs.getString("id"));
                           greet.setContent(rs.getString("content"));
                           result.add(greet);
                       }
                   }
                   return result;
               }
           },"1");

           list.forEach(l ->{
               System.out.println(""+l.toString());
           });
       }catch (Exception e){

       }

    }
}
