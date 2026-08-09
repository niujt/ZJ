package com.wxthxy.zj.controller;

import com.wxthxy.zj.service.QuestionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/zj")
public class TestController {
    @Autowired
    QuestionService service;
   @RequestMapping("/test")
   public ModelAndView index(HttpServletRequest request){
     //  request.setAttribute("questions",service.findAllQuestions("选择题"));
       return new ModelAndView("admin/info/ChoicequestionInfo");
  }
}
