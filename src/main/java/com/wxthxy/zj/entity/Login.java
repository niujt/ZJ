package com.wxthxy.zj.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
/**
 * 登录
 */
public class Login {
    /**主键*/
    private Integer id;
    /**账号*/
    private String username;
    /**密码*/
    private String password;
}
