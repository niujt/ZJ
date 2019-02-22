package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("zj")
public class RegistController {
    @Autowired
    LoginService service;
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
    @ResponseBody
    public JSONObject doRegist(@RequestParam("username")String username,@RequestParam("password")String password,
    @RequestParam("userid")String userid,@RequestParam("identity")String identity){
        System.out.println(username);
        System.out.println(password);
        System.out.println(userid);
        System.out.println(identity);
        JSONObject json=new JSONObject();
        json.put("message",service.doRegist(username,password,userid,identity));
        return json;
    }
}
