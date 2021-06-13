package com.kaitan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

//保存用户上一次访问的时间
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器告诉你你来的时间，把这个时间封装成一个信件，
        //你下次带来，服务器就知道你来了

        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        //若想给client发字符串，需要建立对象
        PrintWriter out = resp.getWriter();

        //cookie,服务器端从客户端获取
        Cookie[] cookies = req.getCookies();//返回数组，说明cookie可能有多个
        //判断cookie是否存在
        if (cookies!=null){
            out.write("The last visit is :");
//            for (Cookie cookie : cookies) {}
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if ( cookie.getName().equals("lastLoginTime")){
                    //获取cookie的值
                    long lastLoginTime = Long.parseLong(cookie.getValue());
                    Date date = new Date(lastLoginTime);
                    out.write(date.toLocaleString());
                }
            }
        }else{
            out.write("This is your first visit");
        }

        //服务器给客户端响应/发送一个cookie
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");
        //设置cookie的时间，有效期为一天
        cookie.setMaxAge(24*60*60);
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
