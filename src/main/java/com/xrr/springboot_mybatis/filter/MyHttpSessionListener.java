package com.xrr.springboot_mybatis.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Description: 监听器
 * @Author: xue.rongrong
 * @Date: 2019/5/21 9:30
 */
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online ++;
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
        online--;
    }

}
