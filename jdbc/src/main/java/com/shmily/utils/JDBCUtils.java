package com.shmily.utils;

import java.sql.*;

/**
 * @Author: liushoulong
 * @Date: 2019/10/26 11:29
 */
public class JDBCUtils {
    // 驱动url
    private static String driverClass = "com.mysql.jdbc.Driver";
    static{
        try{
            Class.forName(driverClass);
        }catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }

    private final static ThreadLocal<Connection> localConn = new ThreadLocal<>();


    private static String dataUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&character=utf8&useSSL=false&useUnicode=true";
    private static String username = "root";
    private static String password = "123456";
    /**
     * 创建连接回话
     */
    public static void getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(dataUrl, username,password );
            System.out.println("生成conn对象 :" + con);
            localConn.set(con);
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    /**
     * 获取sql执行对象
     * @param sql
     * @return
     * @throws SQLException
     */
    public static PreparedStatement getPrepareStatement(String sql) throws SQLException{
        return localConn.get().prepareStatement(sql);
    }


    /**
     * 关闭连接
     * @param con
     * @param statement
     * @param rs
     */
    public static void close(Connection con, Statement statement, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
            if (statement != null){
                statement.close();
            }
            if(con != null){
                con.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }


    /**
     * 关闭连接
     * @param statement
     * @param rs
     */
    public static void close(Statement statement, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
            if (statement != null){
                statement.close();
            }
            if(localConn.get() != null){
                System.out.println("close :" + localConn.get());
                localConn.get().close();
                localConn.set(null);
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }


    public static void main(String[] agrs){
        getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = getPrepareStatement("select * from greet");
            rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                System.out.println("id: "+id);
            }
        } catch (SQLException s){

        }finally {
            close(ps,null);
        }

    }

}
