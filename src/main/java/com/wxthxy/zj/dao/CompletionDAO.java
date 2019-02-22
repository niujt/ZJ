package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Completion;

import java.util.List;

public interface CompletionDAO {
    Integer getCount();
    List<Completion> getAllCompletions();
    int addCompletion(Completion completion);
}
