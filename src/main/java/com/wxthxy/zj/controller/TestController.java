package com.wxthxy.zj.controller;

import com.wxthxy.zj.service.QuestionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zj")
public class TestController {
    @Autowired
    QuestionService service;
   @RequestMapping("/test")
   public String index(HttpServletRequest request){
       request.setAttribute("questions",service.findAllQuestions("选择题"));
       return "/admin/info/ChoicequestionInfo";
  }
}
