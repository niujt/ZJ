package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Answer;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.entity.TeacherCorrection;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.TeacherService;
import com.wxthxy.zj.utils.HomeworkUtils;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("zj")
public class TeacherController {
    @Autowired
    TeacherService service;
    @Autowired
    HomeworkService homeworkService;
    @Autowired
    PaperService paperService;

    /**
     * 老师列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ModelAndView toTeacher(HttpServletRequest request, @RequestParam(value = "currentPage", required = false) Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageBean pageBean = service.getAllTeacher(currentPage);
        request.setAttribute("pageBean", pageBean);
        return new ModelAndView("admin/TeacherManagement");

    }

    /**
     * 添加或者更新老师信息
     *
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/teacher", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addTea(Teacher teacher) {
        JSONObject json = new JSONObject();
        json.put("message", service.addTea(teacher));
        return json;
    }

    /**
     * 删除老师信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delTea(@PathVariable Integer id) {
        JSONObject json = new JSONObject();
        json.put("message", service.delTea(id));
        return json;
    }

    /**
     * 老师作业管理页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher/homework", method = RequestMethod.GET)
    public ModelAndView homework(HttpServletRequest request, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "papername", required = false) String papername
            , @RequestParam(value = "studentname", required = false) String studentname) {
        if (currentPage == null) {
            currentPage = 1;
        }
        PageBean pageBean = homeworkService.getAll(currentPage, papername, studentname);
        request.setAttribute("pageBean", pageBean);
        return new ModelAndView("teacher/homework");
    }

    /**
     * 作业详情
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher/homework/{id}", method = RequestMethod.GET)
    public ModelAndView homework(@PathVariable Integer id, HttpServletRequest request) {
        Map map = homeworkService.getHomeworkAnswer(id);
        HomeWork homeWork = homeworkService.findHomeWork(id);
        Answer answer = (Answer) paperService.getPaperById(homeWork.getPaperid()).get("answers");
        Map scoreMap = HomeworkUtils.scoreMain(map, answer);
        request.setAttribute("id", id);
        request.setAttribute("cpanswers", map.get("cpanswers"));
        request.setAttribute("cqanswers", map.get("cqanswers"));
        request.setAttribute("jqanswers", map.get("jqanswers"));
        request.setAttribute("dpanswers", map.get("dpanswers"));
        request.setAttribute("aqanswers", map.get("aqanswers"));
        request.setAttribute("answers", answer);
        request.setAttribute("cpscore", scoreMap.get("cpscore"));
        request.setAttribute("cqscore", scoreMap.get("cqscore"));
        request.setAttribute("jqscore", scoreMap.get("jqscore"));
        return new ModelAndView("teacher/homeworkinfo");
    }

    /**
     * 老师首页
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/teacher/index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session, HttpServletRequest request) {
        request.setAttribute("teacher", session.getAttribute("message"));
        return new ModelAndView("teacher/index");
    }

    /**
     * 批卷评分
     *
     * @param teacherCorrection
     * @return
     */
    @RequestMapping(value = "/teacher/subhomework", method = RequestMethod.POST)
    public String homework2(TeacherCorrection teacherCorrection) {
        homeworkService.score(teacherCorrection);
        return "redirect:/zj/teacher/homework";
    }

    /**
     * 评价和建议
     *
     * @param homeWork
     * @return
     */
    @RequestMapping(value = "/teacher/evaHomework", method = RequestMethod.POST)
    public String homework3(HomeWork homeWork) {
        homeworkService.eva(homeWork);
        return "redirect:/zj/teacher/homework";
    }

    /**
     * 查重
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacher/check", method = RequestMethod.GET)
    public ModelAndView check(HttpServletRequest request) {
        // request.setAttribute("keys", homeworkService.check());
        int length = 5;
        List<HomeWork> list = homeworkService.getAll();
        List<String> dps = new ArrayList<>();
        List<String> aps = new ArrayList<>();
        String papername = "";
        String studentname = "";
        String stuid = "";
        for (HomeWork homeWork : list) {
            for(String answer:homeWork.getDpanswer().split(";")){
                dps.add(answer);
            }
            for(String answer:homeWork.getApanswer().split(";")){
                aps.add(answer);
            }
        }
        request.setAttribute("homeworks",list);
        request.setAttribute("dps",HomeworkUtils.check(dps, length));
        request.setAttribute("aps", HomeworkUtils.check(aps, length));
        return new ModelAndView("teacher/check");
    }
}
