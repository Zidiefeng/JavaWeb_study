package com.kaitan.service.user;

import com.kaitan.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode, String password);

    //根据user id修改密码
    public boolean updatePwd(int id, String pwd);
}
