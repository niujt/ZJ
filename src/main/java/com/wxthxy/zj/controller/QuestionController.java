package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/questionInfo/{type}",method = RequestMethod.GET)
    public String questionInfo(HttpServletRequest request, @PathVariable String type){
        request.setAttribute("questions",service.findAllQuestions(type));
        if(type.equals("选择题")){
            return "/admin/info/ChoicequestionInfo";
        }
      else{
            return "/admin/info/OtherInfo";
        }
    }
    @RequestMapping(value = "/choiceQuestion",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addChoiceQuestion(Choicequestion obj){
        JSONObject json=new JSONObject();
        json.put("message",service.addChoiceQuestion(obj));
        return json;
    }
    @RequestMapping(value = "/choiceQuestion/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delChoiceQuestion(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.delChoiceQuestion(id));
        return json;
    }
    @RequestMapping(value = "/choiceQuestion/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getChoiceQuestionByid(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("ChoiceQuestion",service.getChoicequestion(id));
        return json;
    }
    @RequestMapping(value = "/upchoiceQuestion",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upChoiceQuestion(Choicequestion choicequestion){
        System.out.println("============="+choicequestion);
        JSONObject json=new JSONObject();
        json.put("message",service.updateChoiceQuestion(choicequestion));
        return json;
    }
}
