package com.wxthxy.zj.dao;

import com.wxthxy.zj.entity.Login;
import org.apache.ibatis.annotations.Param;
public interface LoginDAO {
     Login findLoginByUsername(@Param("username")String username);
}
