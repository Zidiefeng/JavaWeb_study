package com.kaitan.servlet;

import com.kaitan.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        

        //得到SSession
        HttpSession session = req.getSession();

        //给Session中存东西
        session.setAttribute("name",new Person("恺恺",11));

        //获取session的id
        String sessionId = session.getId();

        //判断是不是新的session(session是否新创建)
        if (session.isNew()){
            resp.getWriter().write("session创建成功，id: "+sessionId);
        }else{
            resp.getWriter().write("session已经存在，id: "+sessionId);
        }

        //session创建的时候做了什么
//        Cookie cookie = new Cookie("JSESSIONID", sessionId);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
