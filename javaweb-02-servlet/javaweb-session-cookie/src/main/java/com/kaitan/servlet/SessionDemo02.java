package com.kaitan.servlet;

import com.kaitan.pojo.Person;
import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        //得到SSession
        HttpSession session = req.getSession();

        //给Session中存东西
        Person person = (Person)session.getAttribute("name");

        System.out.println(person.toString());



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
