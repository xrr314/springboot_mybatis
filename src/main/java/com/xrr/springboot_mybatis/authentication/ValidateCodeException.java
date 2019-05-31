package com.xrr.springboot_mybatis.authentication;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/23 10:33
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
