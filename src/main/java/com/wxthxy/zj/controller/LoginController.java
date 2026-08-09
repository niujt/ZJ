package com.wxthxy.zj.controller;
import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.common.ServiceMessage;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.LoginService;
import com.wxthxy.zj.service.StudentService;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    /**
     * 进入首页
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
         return new ModelAndView("index");
    }

    /**
     * 退出登录，删除session缓存
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public ModelAndView loginout(HttpSession session){
        session.removeAttribute("message");
        session.removeAttribute("identity");
        session.removeAttribute("role");
        return new ModelAndView("login");
    }
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject dologin(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("identity") String identity, HttpSession session){
        JSONObject json=new JSONObject();
        Teacher teacher=null;
        if(identity.equals("teacher")){
            teacher=teacherService.findTeadcher(service.findLoginByUserName(username).getId());
            session.setAttribute("message",teacher);
            if(teacher!=null){
                session.setAttribute("role",teacher.getRoleid());
            }
        }
        else if(identity.equals("student")){
            session.setAttribute("message",studentService.findStudent(service.findLoginByUserName(username).getId()));
        }
        else{
            session.setAttribute("message","admin");
        }
        session.setAttribute("identity",identity);
        json.put("login",service.dologin(username,password,identity));
        return  json;

    }
}
