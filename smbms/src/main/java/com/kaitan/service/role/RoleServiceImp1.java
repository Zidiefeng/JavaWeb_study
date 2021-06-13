package com.kaitan.service.role;

import com.kaitan.dao.BaseDao;
import com.kaitan.dao.role.RoleDao;
import com.kaitan.dao.role.RoleDaoImp1;
import com.kaitan.dao.user.UserDao;
import com.kaitan.dao.user.UserDaoImp1;
import com.kaitan.pojo.Role;
import com.kaitan.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class RoleServiceImp1 implements RoleService {
    //引入Dao
    private RoleDao roleDao;
    public RoleServiceImp1(){
        roleDao = new RoleDaoImp1();
    }

    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList =null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                BaseDao.closeResource(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return roleList;
    }
    @Test
    public void test(){
        RoleServiceImp1 roleService = new RoleServiceImp1();
        List<Role> roleList= roleService.getRoleList();
        //System.out.println(Arrays.toString(roleList.toArray()));
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
        }
        System.out.println();
    }
}
