package com.kaitan.dao.user;

import com.kaitan.dao.BaseDao;
import com.kaitan.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp1 implements UserDao{
    public User getLoginUser(Connection connection, String userCode) throws SQLException {

        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;


        if (connection !=null){
            String sql = "Select * from smbms_user where userCode=?";
            Object[] params = {userCode};

            rs = BaseDao.execute(connection, sql,params,rs,pstm);

            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getDate("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getDate("modifyDate"));


            }
            //connection等到业务层再操作
            BaseDao.closeResource(null,pstm,rs);

        }
        return user;
    }

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        PreparedStatement pstm = null;
        int execute=0;
        if (connection !=null){
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object params[]={password, id};
            execute = BaseDao.execute(connection, sql, params, pstm);
            BaseDao.closeResource(connection,pstm, null);
        }
        return execute;
    }
}
