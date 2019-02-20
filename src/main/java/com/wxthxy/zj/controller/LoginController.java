package com.wxthxy.zj.controller;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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
    public String dologin(@RequestParam("username")String username, @RequestParam("password")String password, HttpServletRequest request){
        String message=service.dologin(username,password);
        request.setAttribute("login",message);
       if(!message.equals(ServiceMessage.login_message_03.getText())){
           return "login";
       }
       else{
           return "index";
       }
    }
}
