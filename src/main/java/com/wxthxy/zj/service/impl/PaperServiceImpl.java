package com.wxthxy.zj.service.impl;

import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl  implements PaperService {
    @Autowired
    PaperDAO paperDAO;
    @Autowired
    CompletionDAO completionDAO;
    @Autowired
    ChoicequestionDAO choicequestionDAO;
    @Autowired
    DesignproblemDAO designproblemDAO;
    @Autowired
    JudgementquestionDAO judgementquestionDAO;
    @Autowired
    ApplicationQuestionDAO applicationQuestionDAO;

    @Override
    public List<Paper> getAll() {

        return paperDAO.findAllPapers();
    }
}
