package com.coderzoe.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author yhs
 * @date 2020/6/18 20:48
 * @description 操作数据库公共类
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    //初始化
    static {
        try {
            InputStream resourceAsStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            //加载驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @data: 2020/06/18 20:58
     * @author: yhs
     * @return: {@link Connection }
     * @description: 获得JDBC连接
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void release(Statement statement, Connection connection){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void release(ResultSet resultSet, Statement statement, Connection connection){

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void release(ResultSet resultSet){

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static ResultSet executeQuery(Connection connection,String sql,Object[] params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1,params[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
//        BaseDao.release(preparedStatement,connection);
        return resultSet;
    }


    public static int executeUpdate(Connection connection,String sql,Object[] params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            preparedStatement.setObject(i+1,params[i]);
        }
        int result = preparedStatement.executeUpdate();
        BaseDao.release(preparedStatement,connection);
        return result;
    }
}
