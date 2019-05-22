package com.xrr.springboot_mybatis.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
/**
 * @Description: 过滤器
 * @Author: xue.rongrong
 * @Date: 2019/5/21 9:30
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println(request.getParameter("name"));
        System.out.println("doFilter...");
        chain.doFilter(request, response);
//        HttpServletRequest hrequest = (HttpServletRequest)request;
//        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
//        if(hrequest.getRequestURI().indexOf("/index") != -1 ||hrequest.getRequestURI().indexOf("/asd") != -1 ||
//                hrequest.getRequestURI().indexOf("/online") != -1 ||hrequest.getRequestURI().indexOf("/login") != -1) {
//            chain.doFilter(request, response);
//        }else {
//            wrapper.sendRedirect("/login.do");
//        }
    }

    @Override
    public void destroy() {
    }
}
