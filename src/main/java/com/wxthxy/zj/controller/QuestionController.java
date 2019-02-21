package com.wxthxy.zj.controller;

import com.wxthxy.zj.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 题库
 */
@Controller
@RequestMapping("zj")
public class QuestionController {
    @Autowired
    QuestionService service;
    @RequestMapping(value = "/question",method = RequestMethod.GET)
    public String getQuesList(HttpServletRequest request){
        request.setAttribute("questions",service.getQuestionCounts());
        return "/admin/QuestionManagement";
    }
}
