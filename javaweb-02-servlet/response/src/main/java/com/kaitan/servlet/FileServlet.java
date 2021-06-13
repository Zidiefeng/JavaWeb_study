package com.kaitan.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 要获取下载文件的路径（从哪里下载）
//        String realPath = this.getServletContext().getRealPath("/1.png");
//        得到的是"download path: C:\Users\sunka\Desktop\learn-java\code\javaweb-02-servlet\response\src\main\webapp\1.png",不对
//        最好写死：

        String realPath = "C:\\Users\\sunka\\Desktop\\learn-java\\code\\javaweb-02-servlet\\response\\src\\main\\resources\\1.JPG";

        System.out.println("download path: "+realPath);
        
//        2. 下载的文件名是啥
//        这个方法：找到最后一个/，然后再往后一个就是文件名了
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);

//        3. 想办法让浏览器能够支持下载我们需要的东西
//        注意这个设置可以去google搜索，这里就是支持下载fileName
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));

//        4. 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);

//        5. 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];

//        6. 获取OutputStream对象
//        类似resp.getWriter()
        ServletOutputStream out = resp.getOutputStream();

//        7. 将FileOutputStream流写入到buffer缓冲区,
//           使用OutputStream对象将缓冲区中的数据输出到客户端！
        while((len=in.read(buffer))>0){
            out.write(buffer,0,len);
        }

//        8. 关闭资源
        in.close();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
