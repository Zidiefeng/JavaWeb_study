package com.kaitan.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如何让浏览器3s自动刷新一次
        resp.setHeader("refresh","3");

        //在内存中创建一个图片
        BufferedImage image = new BufferedImage(80,20,BufferedImage.TYPE_INT_BGR);
        //得到图片, 下面的graphics相当于笔
        Graphics2D g = (Graphics2D)image.getGraphics();
        //设置图片的背景颜色,白色的背景框
        g.setColor(Color.WHITE);
        g.fillRect(0,0,80,20);
        //给图片写数据
        g.setColor(Color.blue);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),0,20);

        //告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，需要不让缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-control","no-cache");
        resp.setHeader("Pragma","no-cache");

        //把图片写给浏览器
        ImageIO.write(image,"jpeg", resp.getOutputStream());
    }

    //生成随机数
    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999999)+"";
        //生成七位数
        //下面的操作就是在位数少于7位的时候，生成一个000..的string：sb，然后填充在数字前面
        //比如 1321300 以凑够7位数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7-num.length(); i++) {
            sb.append(0);
        }
        String s = sb.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
