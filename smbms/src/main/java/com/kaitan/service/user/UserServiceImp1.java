package com.kaitan.service.user;

import com.kaitan.dao.BaseDao;
import com.kaitan.dao.user.UserDao;
import com.kaitan.dao.user.UserDaoImp1;
import com.kaitan.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImp1 implements UserService{
    //业务层会调用dao层，所以要引入dao层
    private UserDao userDao;

    //被new出来的时候就实例化了
    public UserServiceImp1(){
        userDao = new UserDaoImp1();
    }
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            //通过业务层调用具体的DB操作
            user = userDao.getLoginUser(connection,userCode);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                BaseDao.closeResource(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return user;
    }

    public boolean updatePwd(int id, String pwd) {
        Connection connection = null;
        boolean flag=false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection,id,pwd)>0){
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            try {
                BaseDao.closeResource(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }

    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int count = 0;
        try {
            connection = BaseDao.getConnection();
            count = userDao.getUserCount(connection, username, userRole);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                BaseDao.closeResource(connection, null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return count;
    }

    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList= null;
        System.out.println("queryUserName --- "+queryUserName);
        System.out.println("queryUserRole --- "+queryUserRole);
        System.out.println("currentPageNo --- "+currentPageNo);
        System.out.println("pageSize --- "+pageSize);

        try {
            connection =BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                BaseDao.closeResource(connection,null,null);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return userList;
    }

    @Test
    public void test(){
        UserServiceImp1 userService = new UserServiceImp1();
        User admin = userService.login("sunlei","1234567");
        System.out.println(admin.getUserPassword());
    }

    @Test
    public void test1(){
        UserServiceImp1 userService = new UserServiceImp1();
        int userCount = userService.getUserCount(null,1);
        System.out.println(userCount);
    }
}
