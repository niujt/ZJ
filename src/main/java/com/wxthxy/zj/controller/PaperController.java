package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.*;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PageBean;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("zj")
public class PaperController {
    @Autowired
    PaperService service;
    @Autowired
    QuestionService questionService;

    /**
     * 试卷列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/paper",method = RequestMethod.GET)
    public String paperList(HttpServletRequest request,@RequestParam(value = "currentPage",required = false)Integer currentPage,@RequestParam(value = "name",required = false)String name){
        if(currentPage==null){
            currentPage=1;
        }
        if(name!=null) {
            name = name.trim();
        }
        else if(name ==null){
            name ="";
        }
        PageBean pageBean=service.getAll(currentPage,name);
        List<Paper> papers=pageBean.getItems();
        PaperUtils.doPaper(papers);
        request.setAttribute("cps",questionService.findAllQuestions("填空题"));
        request.setAttribute("jqs",questionService.findAllQuestions("判断题"));
        request.setAttribute("dps",questionService.findAllQuestions("简答题"));
        request.setAttribute("aqs",questionService.findAllQuestions("应用题"));
        request.setAttribute("cqs",questionService.findAllQuestions("选择题"));
        request.setAttribute("pageBean",pageBean);
        return "/admin/PaperManagement";
    }

    /**
     * 删除试题
     * @param id
     * @return
     */
    @RequestMapping(value ="/paper/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public JSONObject deleterPaper(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("message",service.deletePaperById(id));
        return json;
    }

    /**
     * 试卷详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/paperInfo/{id}",method = RequestMethod.GET)
    public String paperInfo(HttpServletRequest request, @PathVariable Integer id){
        request.setAttribute("paperInfo",service.getPaperById(id));
        return "/admin/info/PaperInfo";
    }

    /**
     * 获得试题答案
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/answer/{id}",method = RequestMethod.GET)
    public String getAnswer( @PathVariable Integer id,HttpServletRequest request){
        request.setAttribute("answers",service.getPaperById(id).get("answers"));
        return "/paper/Answer";
    }

    /**
     * 自动组卷
     * @param autoPaper
     * @return
     */
    @RequestMapping(value = "/paper/autoPaper",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject autoPaper(AutoPaper autoPaper){
        JSONObject json=new JSONObject();
        json.put("message",service.getPaperByAuto(autoPaper));
        return json;
    }

    /**
     * 手动组卷
     * @param manualPaper
     * @return
     */
    @RequestMapping(value = "/paper/manualPaper",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject manualPaper(ManualPaper manualPaper){
        JSONObject json=new JSONObject();
       json.put("message",service.getPaperByManual(manualPaper));
        return json;
    }


}
