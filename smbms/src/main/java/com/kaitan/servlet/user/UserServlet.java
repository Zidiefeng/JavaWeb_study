package com.kaitan.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.kaitan.pojo.User;
import com.kaitan.service.user.UserService;
import com.kaitan.service.user.UserServiceImp1;
import com.kaitan.util.Constants;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//实现Servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        /*method!=null&&method.equals("savepwd"*/
        if ("savepwd".equals(method)){
            this.updatePwd(req,resp);
        }else if ("pwdmodify".equals(method)){
            this.checkPwd(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //修改密码
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        //登陆的时候user信息存到session了，从session拿信息
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        //确定成功再转user
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if (o!=null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImp1();

            flag = userService.updatePwd(((User) o).getId(), newpassword);
            if (flag){
                req.setAttribute("message","修改密码成功，请退出，使用新密码登录");
                //密码修改成功，移除当前session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute("message","修改密码失败");
            }
        }else{
            req.setAttribute("message","新密码有问题");
        }

        try {
            /*not working*/
            req.getRequestDispatcher("pwdmodify.jsp").forward(req,resp);
            //resp.sendRedirect(req.getContextPath()+"/jsp/pwdmodify.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    //验证旧密码,直接跟session对比就好
    public void checkPwd(HttpServletRequest req, HttpServletResponse resp){
        //登陆的时候user信息存到session了，从session拿信息
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        //确定成功再转user
        String oldpassword = req.getParameter("oldpassword");
        //大公司基本都用Map代表结果集
        Map<String, String> resultMap = new HashMap<String, String>();

        if (o==null){
            //session失效，过期
            resultMap.put("result","sessionerror");
        }else if (StringUtils.isNullOrEmpty(oldpassword)){//输入密码为空
            resultMap.put("result","error");
        }else{
            String userPassword=((User) o).getUserPassword();//Session中用户的密码
            if (oldpassword.equals(userPassword)){
                resultMap.put("result","true");
            }else{
                resultMap.put("result","false");
            }
        }


        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //阿里巴巴JSON工具类,用于转换格式
            //现在是map格式，想要转成json,传递给前端
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
