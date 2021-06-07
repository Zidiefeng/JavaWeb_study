package com.kaitan.dao.user;

import com.kaitan.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    //得到要登陆的user
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    //修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

}
