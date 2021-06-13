package com.kaitan.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is demo 4");
        ServletContext context = this.getServletContext();/*不同servlet拿到的是同一个servlet*/
//        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/getp");/*转发的请求路径*/
//        requestDispatcher.forward(req,resp);//调用forward 实现请求转发
        context.getRequestDispatcher("/getp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
