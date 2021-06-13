package com.kaitan.dao.user;

import com.kaitan.dao.BaseDao;
import com.kaitan.pojo.Role;
import com.kaitan.pojo.User;
import com.mysql.jdbc.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    //返回指定username或者userRole的 行数
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs= null;
        int count = 0;

        if (connection != null) {
            //存放参数
            ArrayList<Object> list = new ArrayList<Object>();

            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");
            if (!StringUtils.isNullOrEmpty(username)){
                sql.append(" and u.userName like ?");
                list.add("%"+username+"%"); // index 0
            }
            if(userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);// index 1
            }

            //list转换为数组
            Object[] params = list.toArray();

            System.out.println("UserDaoImp1 --- getUserCount: "+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            if (rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.closeResource(null,pstm,rs);
        }
        return count;
    }

    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {

        PreparedStatement pstm= null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if (connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*, r.roleName as userRoleName from smbms_user u, smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if (userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo= (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql---->"+sql.toString());
            rs = BaseDao.execute(connection, sql.toString(), params,rs,pstm );
            while(rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseDao.closeResource(null,pstm,rs);
        }

        return userList;
    }


}
