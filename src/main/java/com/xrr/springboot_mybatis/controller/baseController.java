package com.xrr.springboot_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/22 17:54
 */
@Controller
public class baseController {

//    @RequestMapping("/")
    public String init(){
        return "/src/main/resources/static/login.html";
    }
}
