package com.wxthxy.zj.utils;

/**
 * 分页处理类
 */
public class PageUtils {
    /**
     * 每页最大显示条数
     */
    public static Integer PageSize=5;

    public static Integer pageMax(Integer count){
        return (count  +  PageSize  - 1) / PageSize;
    }

}
