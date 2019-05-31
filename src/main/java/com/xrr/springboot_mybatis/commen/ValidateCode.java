package com.xrr.springboot_mybatis.commen;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/23 11:31
 */
public class ValidateCode {

    /** 验证码 */
    private String code;

    /** 判断过期时间 */
    private LocalDateTime expireTime;


    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
