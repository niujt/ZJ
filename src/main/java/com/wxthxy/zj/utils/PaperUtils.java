package com.wxthxy.zj.utils;

import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Paper;

import java.util.*;

/**
 * 试卷工具类
 */
public class PaperUtils {
    /**
     * 计算每种题型一共有多少，并赋值
     * @param papers
     */
    public static void doPaper(List<Paper> papers){
        for(Paper paper:papers){
            paper.setCpCount(paper.getCp().split(",").length);
            paper.setAqCount(paper.getAq().split(",").length);
            paper.setCqCount(paper.getCq().split(",").length);
            paper.setDpCount(paper.getDp().split(",").length);
            paper.setJqCount(paper.getJq().split(",").length);

        }
    }

    /**
     * 获取题号
     * @param str
     * @return
     */
    public static List<Integer> getQuestionIds(String str){
        String[] ids=str.split(",");
        List<Integer> _ids=new ArrayList<>();
        for(String id:ids){
            Integer _id=Integer.parseInt(id);
            _ids.add(_id);
        }
        return _ids;
    }

    /**
     * 获取随机试题题号
     * @param ids 数据库获取的题号字符串
     * @param number 题数
     * @return  随机试题集合
     */
    public static String autoQustionId(List<Integer> ids,Integer number){
        Map map = new HashMap();
        String str="";
       // List listNew = new ArrayList();
            while (map.size() < number) {
                int random = (int) (Math.random() * ids.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    str+=ids.get(random)+",";
                }
            }
            str=str.substring(0,str.length()-1);
            return str;
    }
    public static String array2String (String[] message){
        String ids="";
        for(String str:message){
            ids+=str+",";
        }
        ids=ids.substring(0,ids.length()-1);
        return ids;
    }
}
