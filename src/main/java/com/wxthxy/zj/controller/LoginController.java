package com.wxthxy.zj.controller;
import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Login;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("zj")
public class LoginController {
    @Autowired
    public LoginService service;
    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }
    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dologin(@RequestParam("username")String username, @RequestParam("password")String password){
        JSONObject json=new JSONObject();
        json.put("login",service.dologin(username));
        return json;
    }
}
