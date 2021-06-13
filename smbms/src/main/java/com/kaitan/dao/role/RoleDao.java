package com.kaitan.dao.role;

import com.kaitan.pojo.Role;
import com.kaitan.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    //獲取role list
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
