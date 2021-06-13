package com.kaitan.servlet.user;

import com.kaitan.pojo.User;
import com.kaitan.service.user.UserServiceImp1;
import com.kaitan.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //控制层servlet要调用业务层
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start ...");
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        UserServiceImp1 userServiceImp1 = new UserServiceImp1();
        // found the user
        User user = userServiceImp1.login(userCode, userPassword);
        if (user!=null){
            //查有此人，可以登录
            //将用户信息放到session中
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到主页
            resp.sendRedirect(req.getContextPath()+"/jsp/frame.jsp");
        }else{
            //查无此人，转到登陆页面，提示他有错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
