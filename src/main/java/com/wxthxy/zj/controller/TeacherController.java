package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("zj")
public class TeacherController {
    @Autowired
    TeacherService service;
    @Autowired
    HomeworkService homeworkService;
    /**
     * 老师列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    public  String toTeacher(HttpServletRequest request){
        request.setAttribute("teachers",service.getAllTeacher());
        return "/admin/TeacherManagement";

    }

    /**
     * 添加或者更新老师信息
     * @param teacher
     * @return
     */
    @RequestMapping(value ="/teacher",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTea(Teacher teacher){
        JSONObject json=new JSONObject();
        json.put("message",service.addTea(teacher));
        return json;
    }

    /**
     * 删除老师信息
     * @param id
     * @return
     */
    @RequestMapping(value ="/teacher/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delTea(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.delTea(id));
        return json;
    }

    @RequestMapping(value ="/teacher/homework",method = RequestMethod.GET)
    public String homework(HttpServletRequest request){
        request.setAttribute("homeworks",homeworkService.getAll());
        return "/teacher/homework";
    }

}
