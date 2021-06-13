package com.kaitan.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计网站在线人数：统计一共有多少个session（因为一个session就代表一个人）
public class OnlineCountListener implements HttpSessionListener {
    //创建Session监听 看你的一举一动
    //一旦创建Session就会触发一次这个event
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        System.out.println(se.getSession().getId());
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if (onlineCount==null){
            onlineCount=new Integer(1);
        }else {
            int count = onlineCount.intValue();
            onlineCount= new Integer(count+1);
        }
        ctx.setAttribute("OnlineCount", onlineCount);
    }

    //销毁Session监听
    //一旦session销毁，就会触发这个event
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
        if (onlineCount==null){
            onlineCount=new Integer(0);
        }else {
            int count = onlineCount.intValue();
            onlineCount= new Integer(count-1);
        }
        ctx.setAttribute("OnlineCount", onlineCount);    }
}
