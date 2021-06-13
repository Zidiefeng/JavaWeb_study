package com.kaitan.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter初始化");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        System.out.println("Before CharacterEncodingFilter ");
        filterChain.doFilter(servletRequest,servletResponse);//让我们的请求继续走，如果不写，到这里程序会拦截停止
        System.out.println("After CharacterEncodingFilter ");
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
