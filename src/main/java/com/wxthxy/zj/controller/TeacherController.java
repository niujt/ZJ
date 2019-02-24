package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.entity.TeacherCorrection;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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

    /**
     * 老师作业管理页面
     * @param request
     * @return
     */
    @RequestMapping(value ="/teacher/homework",method = RequestMethod.GET)
    public String homework(HttpServletRequest request){
        request.setAttribute("homeworks",homeworkService.getAll());
        return "/teacher/homework";
    }

    /**
     * 作业详情
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value ="/teacher/homework/{id}",method = RequestMethod.GET)
    public String homework(@PathVariable Integer id,HttpServletRequest request){
        Map map=homeworkService.getHomeworkAnswer(id);
        request.setAttribute("id",id);
        request.setAttribute("cpanswers", map.get("cpanswers"));
        request.setAttribute("cqanswers",map.get("cqanswers"));
        request.setAttribute("jqanswers",map.get("jqanswers"));
        request.setAttribute("dpanswers",map.get("dpanswers"));
        request.setAttribute("aqanswers",map.get("aqanswers"));
        return "/teacher/homeworkinfo";
    }

    /**
     * 老师首页
     * @param session
     * @return
     */
    @RequestMapping(value ="/teacher/index",method = RequestMethod.GET)
    public String index(HttpSession session,HttpServletRequest request){
        request.setAttribute("teacher",session.getAttribute("message"));
        return "/teacher/index";
    }

    /**
     * 批卷评分
     * @param teacherCorrection
     * @return
     */
    @RequestMapping(value ="/teacher/subhomework",method = RequestMethod.POST)
    public String homework2(TeacherCorrection teacherCorrection){
        homeworkService.score(teacherCorrection);
        return "redirect:/zj/teacher/homework";
    }
    /**
     * 评价和建议
     * @param homeWork
     * @return
     */
    @RequestMapping(value ="/teacher/evaHomework",method = RequestMethod.POST)
    public String homework3(HomeWork homeWork){
        homeworkService.eva(homeWork);
        return "redirect:/zj/teacher/homework";
    }
}
