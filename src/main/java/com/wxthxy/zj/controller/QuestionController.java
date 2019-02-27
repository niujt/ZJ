package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 题库
 */
@Controller
@RequestMapping("zj")
public class QuestionController {
    @Autowired
    QuestionService service;

    /**
     * 题库管理界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/question",method = RequestMethod.GET)
    public String getQuesList(HttpServletRequest request){
        request.setAttribute("questions",service.getQuestionCounts());
        return "/admin/QuestionManagement";
    }

    /**
     * 题目详情
     * @param request
     * @param type
     * @return
     */
    @RequestMapping(value = "/questionInfo/{type}",method = RequestMethod.GET)
    public String questionInfo(HttpServletRequest request, @PathVariable String type,@RequestParam(value = "pageNum",required = false)Integer pageNum){
        int start;
        if(pageNum==null||pageNum<=1){
            pageNum=1;
            start=0;
        }
        if(pageNum> PageUtils.pageMax(service.getQuestionCounts().get(type))){
            pageNum=PageUtils.pageMax(service.getQuestionCounts().get(type));
        }
        start=PageUtils.PageSize*(pageNum-1);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("questions",service.findAllQuestions(type,start,PageUtils.PageSize));
        request.setAttribute("type",type);
        if(type.equals("选择题")){
            return "/admin/info/ChoicequestionInfo";
        }
        else if(type.equals("应用题")){
            return "/admin/info/ApplicationquestionInfo";
        }
      else{
            return "/admin/info/OtherInfo";
        }
    }

    /**
     * 添加选择题
     * @param obj
     * @return
     */
    @RequestMapping(value = "/choiceQuestion",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addChoiceQuestion(Choicequestion obj){
        JSONObject json=new JSONObject();
        json.put("message",service.addChoiceQuestion(obj));
        return json;
    }

    /**
     * 删除选择题
     * @param id
     * @return
     */
    @RequestMapping(value = "/choiceQuestion/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delChoiceQuestion(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.delChoiceQuestion(id));
        return json;
    }

    /**
     * 选择题详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/choiceQuestion/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getChoiceQuestionByid(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("ChoiceQuestion",service.getChoicequestion(id));
        return json;
    }

    /**
     * 更新选择题
     * @param choicequestion
     * @return
     */
    @RequestMapping(value = "/upchoiceQuestion",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upChoiceQuestion(Choicequestion choicequestion){
        JSONObject json=new JSONObject();
        json.put("message",service.updateChoiceQuestion(choicequestion));
        return json;
    }

    /**
     * 删除应用题
     * @param id
     * @return
     */
    @RequestMapping(value = "/ApplicationQuestion/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delApplicationQuestion(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.deleteAppQue(id));
        return json;
    }

    /**
     * 添加填空，判断，简答题
     * @param question
     * @return
     */
    @RequestMapping(value = "/addOthers",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addCompletion(Question question){
        JSONObject json=new JSONObject();
        json.put("message",service.addQuestion(question));
        return json;
    }
    /**
     * 删除填空，判断，简答题
     * @param
     * @return
     */
    @RequestMapping(value = "/delOthers",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject delOthers(@RequestParam("type") String type,@RequestParam("id") Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.delQuestion(id, type));
        return json;
    }
    /**
     * 更新填空，判断，简答题
     * @param
     * @return
     */
    @RequestMapping(value = "/updateOthers",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject updateOthers(Question question){
        JSONObject json=new JSONObject();
        json.put("message",service.updateQuestion(question));
        return json;
    }
    /**
     * 根据id查找填空，判断，简答题
     * @param
     * @return
     */
    @RequestMapping(value = "/findOther",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject updateOthers(@RequestParam("type") String type,@RequestParam("id") Integer id){
        JSONObject json=new JSONObject();
        json.put("otherQue",service.findQuestionByid(id, type));
        return json;
    }
}
