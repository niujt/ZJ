package com.wxthxy.zj.controller;
import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.service.LoginService;
import com.wxthxy.zj.service.StudentService;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("zj")
public class LoginController {
    @Autowired
    public LoginService service;
    @Autowired
    public TeacherService teacherService;
    @Autowired
    public StudentService studentService;
    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(){
        return "/login";
    }
    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/index";
    }

    /**
     * 退出登录，删除session缓存
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public String loginout(HttpSession session){
        session.removeAttribute("message");
        return "/login";
    }
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dologin(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("identity") String identity, HttpSession session){
        JSONObject json=new JSONObject();
        if(identity.equals("teacher")){
            session.setAttribute("message",teacherService.findTeadcher(service.findLoginByUserName(username).getId()));
        }
        else if(identity.equals("student")){
            session.setAttribute("message",studentService.findStudent(service.findLoginByUserName(username).getId()));
        }
        else{

        }
        json.put("login",service.dologin(username,password));
        return  json;

    }
}
