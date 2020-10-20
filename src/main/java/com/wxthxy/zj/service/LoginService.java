package com.wxthxy.zj.service;


import com.wxthxy.zj.entity.Login;

public interface LoginService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    String dologin(String username, String password,String identity);
    Login findLoginByUserName(String username);
    /**
     * 注册
     * @param username 账号
     * @param password 密码
     * @param userid 工号或学号
     * @param identity 身份
     * @return
     */
    String doRegist(String username,String password,String userid,String identity);
}
