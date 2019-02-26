package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.StudentService;
import com.wxthxy.zj.utils.HomeworkUtils;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("zj")
public class StudentController {
    @Autowired
    StudentService service;
    @Autowired
    PaperService paperService;
    @Autowired
    HomeworkService homeworkService;

    /**
     * 学生列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String stulist(HttpServletRequest request){
        request.setAttribute("students",service.findAllStudents());
        return "/admin/StudentManagement";
    }

    /**
     * 添加或更新学生
     * @param student
     * @return
     */
    @RequestMapping(value ="/student",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addStu(Student student){
        JSONObject json=new JSONObject();
        json.put("message",service.addStudent(student));
        return json;
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @RequestMapping(value ="/student/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delstu(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.delStu(id));
        return json;
    }
    /**
     * 学生做考卷
     * @param id
     * @return
     */
    @RequestMapping(value ="/student/homework/{id}",method = RequestMethod.GET)
    public String doHomework(HttpServletRequest request,@PathVariable Integer id){
        request.setAttribute("paperInfo",paperService.getPaperById(id));
        return "/student/HomeworkInfo";
    }
    /**
     * 学生作业列表
     * @param request
     * @return
     */
    @RequestMapping(value ="/student/homework",method = RequestMethod.GET)
    public String doHomework(HttpServletRequest request,HttpSession session){
        Student student=(Student)session.getAttribute("message");
        request.setAttribute("homeworks",homeworkService.getAllByStuid(student.getStuid()));
        return "/student/homework";
    }
    /**
     * 学生首页
     * @param session
     * @return
     */
    @RequestMapping(value ="/student/index",method = RequestMethod.GET)
    public String index(HttpSession session, HttpServletRequest request){
        request.setAttribute("student",session.getAttribute("message"));
        System.out.println((Student)session.getAttribute("message"));
        return "/student/index";
    }

    @RequestMapping(value = "/student/subhomework",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject subhomework(@RequestBody Map<String,String> map,HttpSession session){
        JSONObject json=new JSONObject();
        Student student=(Student)session.getAttribute("message");
        System.out.println(student);
        HomeWork homeWork=HomeworkUtils.subHomework(map,student);
        json.put("message",homeworkService.subHomework(homeWork));
        return json;
    }
}
