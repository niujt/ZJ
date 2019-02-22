package com.wxthxy.zj.controller;

import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.utils.PaperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        //System.out.println(service.getPaperById(id));
        return "/admin/info/PaperInfo";
    }
}
