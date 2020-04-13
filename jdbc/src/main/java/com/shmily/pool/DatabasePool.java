package com.shmily.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * @Author: liushoulong
 * @Date: 2019/10/27 11:41
 * 数据库连接池
 */
public  class DatabasePool implements IPool {

    /**
     * 驱动名称
     */
    private static String driverName = null;
    /**
     * 默认配置文件在classpath路径下
     * 默认配置文件名称 database.properties
     */
    private static String propertiesName = "database.properties";
    /**
     * 连接池实例，该实例定义成类static，并且是单例
     */
    private static DatabasePool instance = null;

    /**
     * 驱动实例
     */
    private static Driver driver = null;

    /**
     * jdbc连接url
     */
    private static String jdbcUrl;
    /**
     * 用户名
     */
    private static String username;

    /**
     * 连接密码
     */
    private static String password;

    /**
     * 最大连接数
     */
    private static int maxConnectNum = 200;

    /**
     * 最小连接数（初始化连接数）
     */
    private static int minConectNum = 10;

    /**
     * 矢量集合
     * 存放连接对象的集合
     * vector特点: 数组结构，线程安全、队列特性、容量扩充、元素长度自增
     */
    private static Vector<Connection> freeConnection = new Vector<>();

    // 当前有效连接数(在线连接数)，包括空闲连接数、使用连接数
    private static int validNum;
    // 空闲连接数
    private static int freeNum;


    /**
     * 初始化
     * 加载配置文件
     */
    @Override
    public void init() {
        // 加载资源
        InputStream is = DatabasePool.class.getResourceAsStream(propertiesName);
        Properties properties = new Properties();
        try{
            properties.load(is);
        } catch (IOException io){
            io.printStackTrace();
        }
        driverName = properties.getProperty("driver-class");
        jdbcUrl =  properties.getProperty("jdbc-url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    /**
     * 加载驱动
     */
    private void loadDriver(){
        try{
            driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e){
            e.printStackTrace();
        }
    }

    private DatabasePool(){
        // 初始化配置文件
        init();
        // 初始化驱动
        loadDriver();
        // 初始化连接池
        initConnection();
    }

    /**
     * 初始化数据库连接池实例
     * @return
     */
    public static synchronized DatabasePool getInstance(){
        if(instance == null){
            instance = new DatabasePool();
        }
        return instance;
    }

    /**
     * 第一次加载
     */
    private void initConnection(){
        // 判断 初始化容器大小和最大容器大小
        if(maxConnectNum <= 0){
            maxConnectNum = 20;
        }

        if(minConectNum <= 0){
            minConectNum = 10;
        }

        if(maxConnectNum < minConectNum){
            throw new IllegalArgumentException("最大连接数小于最小连接数");
        }
        try{
            for(int i=0; i <minConectNum;i++){
                Connection conn = null;
                conn = createdConnection();

                freeConnection.add(conn);
            }
        } catch (SQLException s){
            s.printStackTrace();
        }
        // 在线连接数
        validNum = freeConnection.size();
        freeNum = validNum;
    }

    private static Connection createdConnection() throws SQLException {
        Connection conn;
        if(username == null || username.trim() == "" || username.length() <= 0){
            conn = DriverManager.getConnection(jdbcUrl);
        } else {
            conn = DriverManager.getConnection(jdbcUrl,username ,password );
        }
        return conn;
    }

    public static synchronized Connection getConnection() throws SQLException{

        Connection con = null;
         if(freeConnection.size() > 0){
             con = freeConnection.firstElement();
             // 移除
             freeConnection.removeElementAt(0);
             if(con == null || con.isClosed()){
                 validNum --;
             }
             freeNum --;

         } else if(maxConnectNum < validNum){
             con = createdConnection();
             validNum++;
         }
         return con;
    }

    /**
     * 驱动注销
     */
    public static synchronized void destroyDriver(){
        if(driver != null){
            try{
                DriverManager.deregisterDriver(driver);
            }catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }

    /**
     * 空闲数量
     * @return
     */
    public static int getFreeNum(){
        return freeConnection.size();
    }

    /**
     * 被使用数量
     * @return
     */
    public static int getUserNum(){
        return validNum - freeConnection.size();
    }

}
