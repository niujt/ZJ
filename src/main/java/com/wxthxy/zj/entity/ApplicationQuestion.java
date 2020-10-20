package com.wxthxy.zj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


/**
 * 应用题
 */
@Getter
@Setter
@ToString
public class ApplicationQuestion extends Question{
    /**答案贴图*/
    @JsonIgnore
    private MultipartFile imgfile;
    private String imgurl;
}
