package com.kaitan.service.user;

import com.kaitan.pojo.User;

import java.util.List;

public interface UserService {
    //用户登录
    public User login(String userCode, String password);

    //根据user id修改密码
    public boolean updatePwd(int id, String pwd);

    //查询record数
    public int getUserCount(String username, int userRole);

    //根据条件查询user list
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
}
