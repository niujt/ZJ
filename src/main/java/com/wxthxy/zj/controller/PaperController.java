package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.wxthxy.zj.entity.AutoPaper;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.service.PaperService;
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

    /**
     * 试卷列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/paper",method = RequestMethod.GET)
    public String paperList(HttpServletRequest request){
        List<Paper> papers=service.getAll();
        PaperUtils.doPaper(papers);
        request.setAttribute("papers",papers);
        return "/admin/PaperManagement";
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

}
