package com.kaitan.servlet;

import com.kaitan.util.Constant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取前端request的参数
        String username = req.getParameter("username");
        if (username.equals("admin")){
            //登陆成功
            req.getSession().setAttribute(Constant.USER_SESSION,req.getSession().getId());
            resp.sendRedirect("/javaweb_filter_war_exploded/sys/success.jsp");
        }else{//登陆失败
            resp.sendRedirect("/javaweb_filter_war_exploded/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
