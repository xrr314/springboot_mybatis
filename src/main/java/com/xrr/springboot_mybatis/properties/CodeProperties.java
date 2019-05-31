package com.xrr.springboot_mybatis.properties;

import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/23 11:20
 */
@Configuration
public class CodeProperties {

    private int rememberMeSeconds = 3600;

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
