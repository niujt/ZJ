package com.wxthxy.zj.controller;

import com.wxthxy.zj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("zj")
public class StudentController {
    @Autowired
    StudentService service;
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String stulist(HttpServletRequest request){
        request.setAttribute("students",service.findAllStudents());
        return "/admin/StudentManagement";
    }
}
