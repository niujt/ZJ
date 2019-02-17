package com.wxthxy.zj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("zj")
public class RegistController {
    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.GET)
    public String regist(){
        return "/regist";
    }

    /**
     * 添加用户，返回登录页面
     * @return
     */
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public String doRegist(){
        return "/login";
    }
}
