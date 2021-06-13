package com.kaitan.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    //类加载的时候就初始化了
    static{
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver= properties.getProperty("driver");
        url= properties.getProperty("url");
        username= properties.getProperty("username");
        password= properties.getProperty("password");
    }

    //获取DB的连接
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //编写查询的公共类
    public static ResultSet execute(Connection connection, String sql,Object[] params, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i =0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    //编写增删改的公共方法
    public static int execute(Connection connection, String sql,Object[] params, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i =0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;

    }

    //释放资源
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet ) throws SQLException {
        boolean flag=true;
        if (resultSet!=null){
            try{
                resultSet.close();
                resultSet=null;
            }catch(SQLException e){
                e.printStackTrace();
                flag=false;
            }
        }

        if (preparedStatement!=null){
            try{
                preparedStatement.close();
                preparedStatement=null;
            }catch(SQLException e){
                e.printStackTrace();
                flag=false;
            }
        }

        if (connection!=null){
            try{
                connection.close();
                connection=null;
            }catch(SQLException e){
                e.printStackTrace();
                flag=false;
            }
        }
        return flag;
    }
}
