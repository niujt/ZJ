package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Answer;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.entity.Student;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.StudentService;
import com.wxthxy.zj.utils.HomeworkUtils;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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
    public String stulist(HttpServletRequest request,@RequestParam(value = "currentPage",required = false)Integer currentPage){
        if(currentPage==null){
            currentPage=1;
        }
        PageBean pageBean=service.findAllStudents(currentPage);
        request.setAttribute("pageBean",pageBean);
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
    public String doHomework(HttpServletRequest request,HttpSession session,@RequestParam(value = "currentPage",required = false)Integer currentPage){
        if(currentPage==null){
            currentPage=1;
        }
        Student student=(Student)session.getAttribute("message");
        request.setAttribute("pageBean",homeworkService.getAllByStuid(student.getStuid(),currentPage));
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
        return "/student/index";
    }

    /**
     * 提交作业
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/student/subhomework",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject subhomework(@RequestBody Map<String,String> map,HttpSession session){
        JSONObject json=new JSONObject();
        Student student=(Student)session.getAttribute("message");
        HomeWork homeWork=HomeworkUtils.subHomework(map,student);
        json.put("message",homeworkService.subHomework(homeWork));
        return json;
    }

    /**
     * 自批
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/student/subhomeworkBySelf",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject subhomeworkBySelf(@RequestBody Map<String,String> map,HttpSession session){
        JSONObject json=new JSONObject();
        Student student=(Student)session.getAttribute("message");
        HomeWork homeWork=HomeworkUtils.subHomework(map,student);
      json.put("message",homeworkService.subHomeworkBySelf(homeWork));
        return json;
    }

    /**
     * 自测结果页面
     * @param request
     * @param paperid
     * @param id
     * @return
     */
    @RequestMapping(value = "/student/DoBySelf",method = RequestMethod.GET)
    public String DoBySelf(HttpServletRequest request,@RequestParam("paperid")Integer paperid,@RequestParam("id")Integer id){
        Map map=homeworkService.getHomeworkAnswer(id);
        Answer answer=(Answer) paperService.getPaperById(paperid).get("answers");
        request.setAttribute("cpanswers", map.get("cpanswers"));
        request.setAttribute("cqanswers",map.get("cqanswers"));
        request.setAttribute("jqanswers",map.get("jqanswers"));
        request.setAttribute("answers",answer);
        HomeWork homeWork=new HomeWork();
        homeWork.setId(id);
        homeWork.setScore(scoreBySelf(map,answer));
        homeworkService.scoreBySelf(homeWork);
     return "/student/DoBySelf";
    }

    /**
     * 自测得分
     */
    private String scoreBySelf(Map map, Answer answer){
        //填空题答案
        double score=0.0;
        List<String> cpanswers=(List<String>)map.get("cpanswers");
        for(int i=0;i<cpanswers.size();i++){
            String an=answer.getCpanwsers().get(i);
            an=an.substring(2,an.length());
            if(cpanswers.get(i).equals(an)){
                score=score+5;
            }
        }
        //选择题答案
        List<String> cqanswers=(List<String>)map.get("cqanswers");
        for(int i=0;i<cqanswers.size();i++){
            String an=answer.getCqanwsers().get(i);
            an=an.substring(2,an.length());
            if(cqanswers.get(i).equals(an)){
                score=score+2;
            }
        }
        //判断题答案
        List<String> jqanswers=(List<String>)map.get("jqanswers");
        for(int i=0;i<jqanswers.size();i++){
            String an=answer.getJqanwsers().get(i);
            an=an.substring(2,an.length());
            if(jqanswers.get(i).equals(an)){
                score=score+1;
            }
        }
        return score+"";
    }

}
