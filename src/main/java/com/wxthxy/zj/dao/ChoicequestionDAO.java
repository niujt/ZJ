package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Choicequestion;

import java.util.List;

public interface ChoicequestionDAO {
    Integer getCount();
   List<Choicequestion> findAllChoicequestion();
}
