package com.wxthxy.zj.controller;

import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("zj")
public class TeacherController {
    @Autowired
    TeacherService service;
    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    public  String toTeacher(HttpServletRequest request){
        request.setAttribute("teachers",service.getAllTeacher());
        return "/admin/TeacherManagement";

    }
}
