package com.xrr.springboot_mybatis.commen;

public interface SmsCodeSender {

    void send(String mobile, String code);
}
