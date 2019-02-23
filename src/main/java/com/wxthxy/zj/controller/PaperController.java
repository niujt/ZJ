package com.wxthxy.zj.controller;

import com.wxthxy.zj.entity.Answer;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/answer/{id}",method = RequestMethod.GET)
    public String getAnswer( @PathVariable Integer id,HttpServletRequest request){
        request.setAttribute("answers",service.getPaperById(id).get("answers"));
        return "/paper/Answer";
    }

}
