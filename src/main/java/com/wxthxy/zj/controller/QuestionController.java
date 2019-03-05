package com.wxthxy.zj.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wxthxy.zj.entity.ApplicationQuestion;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLDecoder;

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
    public String questionInfo(HttpServletRequest request, @PathVariable String type,@RequestParam(value = "currentPage",required = false)Integer currentPage){
        if(currentPage==null){
            currentPage=1;
        }
        PageBean pageBean=service.findAllQuestions(type,currentPage);
        request.setAttribute("pageBean",pageBean);
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
     * 添加应用题
     * @param applicationQuestion
     * @return
     */
    @RequestMapping(value = "/applicationQue",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addapplicationQue(ApplicationQuestion applicationQuestion){
        String path = QuestionController.class.getResource("QuestionController.class") .toString();
        JSONObject json=new JSONObject();
        try{
            path = URLDecoder.decode(path);
            path.replaceAll("\\\\", "/");
            path=path.substring(path.indexOf(":")+2,path.indexOf("target"));
            path=path+"src/main/resources/static/appimg/"+applicationQuestion.getImgfile().getOriginalFilename();
           File file= new File(path);
           if(!file.exists()){
               new File(path).createNewFile();
           }
            applicationQuestion.getImgfile().transferTo(file);
            applicationQuestion.setImgurl("/appimg/"+applicationQuestion.getImgfile().getOriginalFilename());
            json.put("message",service.addApplicationQue(applicationQuestion));
        }catch (Exception e){
            json.put("message","上传文件过大");
         e.printStackTrace();
        }
        return json;
    }

    /**
     * 添加应用题
     * @param applicationQuestion
     * @return
     */
    @RequestMapping(value = "/upapplicationQue",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upapplicationQue(ApplicationQuestion applicationQuestion){
        String path = QuestionController.class.getResource("QuestionController.class") .toString();
        JSONObject json=new JSONObject();
        try{
            path = URLDecoder.decode(path);
            path.replaceAll("\\\\", "/");
            path=path.substring(path.indexOf(":")+2,path.indexOf("target"));
            path=path+"src/main/resources/static/appimg/"+applicationQuestion.getImgfile().getOriginalFilename();
            File file= new File(path);
            if(!file.exists()){
                new File(path).createNewFile();
            }
            applicationQuestion.getImgfile().transferTo(file);
            applicationQuestion.setImgurl("/appimg/"+applicationQuestion.getImgfile().getOriginalFilename());
            json.put("message",service.updateApplication(applicationQuestion));
        }catch (Exception e){
            json.put("message","上传文件过大");
            e.printStackTrace();
        }
        return json;
    }




    /**
     * 应用题详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/applicationQue/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject upapplicationQue(@PathVariable Integer id){
        JSONObject json=new JSONObject();
        json.put("application",service.loadApplication(id));
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
