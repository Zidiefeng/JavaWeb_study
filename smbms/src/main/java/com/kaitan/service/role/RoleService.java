package com.kaitan.service.role;

import com.kaitan.pojo.Role;
import com.kaitan.pojo.User;

import java.util.List;

public interface RoleService {
    //根据条件查询user list
    public List<Role> getRoleList();
}
