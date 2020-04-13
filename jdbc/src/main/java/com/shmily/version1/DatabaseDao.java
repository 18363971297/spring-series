package com.shmily.version1;

import com.shmily.model.Greet;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 9:08
 */
public class DatabaseDao {


    public static void save(Greet greet){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 连接数据库获得连接对象
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false", "root","123456" );
            //long id = System.currentTimeMillis();
            // 使用? 进行占位
            // 构建statement sql语句对象
            ps = conn.prepareStatement("insert into (id,conment,name) values (?,?,?)");
            // 填充参数
            ps.setString(1, greet.getId());
            ps.setString(2,greet.getContent());
            ps.setString(3, greet.getName());
            // 执行dml操作 使用该语句，返回执行的节骨
            int flag = ps.executeUpdate();
            System.out.println("保存结果:"+flag);

            // connection 对于事务，默认自动执行
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }finally {
            if(ps != null){
                try{
                    ps.close();
                } catch (SQLException s){

                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException s){

                }
            }
        }

        // 获取statement 语句对象
        //conn.createStatement()
        // 调用执行器


        // 接收结果

        // 显示
    }



    public static void select(String id){
        String sql = "select * from greet where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        // 加载驱动
           Class.forName("com.mysql.jdbc.Driver");
        // 获取connection回话连接
           conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false","root","123456" );
        // 获取sql对象
           ps = conn.prepareStatement(sql);
           ps.setString(1, id);
        // 执行sql
           rs = ps.executeQuery();
        // 获取结果
            List<Greet> result = new ArrayList<>();
           while(rs.next()){
               Greet greet = new Greet();
               greet.setId(rs.getString("id"));
               greet.setContent(rs.getString("content"));
               greet.setName(rs.getString("name"));
               result.add(greet);
           }
           result.forEach(r->{
                System.out.println(r.toString());
           });
        // 处理结果
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        } catch (SQLException sqle) {

        }finally {
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null) {
                    ps.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        select("1");
        select("2");
    }


    /**
     * 获取conn回话连接信息
     * @param connection
     */
    public static void getMetaData(Connection connection) throws SQLException{
        connection.getMetaData();
    }
}
