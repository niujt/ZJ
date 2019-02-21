package com.wxthxy.zj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("zj")
public class PaperController {
    @RequestMapping(value = "/paper",method = RequestMethod.GET)
    public String paperList(){
        return "/admin/PaperManagement";
    }
}
