package com.kaitan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

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
                if ( cookie.getName().equals("name")){

                    System.out.println(URLDecoder.decode(cookie.getValue(),"utf-8"));


                    //获取cookie的值
                    //System.out.println(cookie.getValue());
                }
            }
        }else{
            out.write("This is your first visit");
        }
        Cookie cookie = new Cookie("name", URLEncoder.encode("哈哈","utf-8"));
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
