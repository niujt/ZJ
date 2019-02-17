package com.wxthxy.zj.controller;
import com.wxthxy.zj.entity.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("zj")
public class LoginController {
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
    public Object dologin(@RequestParam("username")String username,@RequestParam("password")String password){
        System.out.println(username+","+password);
        return username+","+password;
    }
}
