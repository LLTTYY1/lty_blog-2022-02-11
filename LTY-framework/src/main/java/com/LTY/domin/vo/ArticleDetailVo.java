package com.LTY.domin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 刘泰源
 * @version 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {
    private Long id;
    //文章内容
    private String content;
    private Date createTime;
    //是否允许评论 1是，0否
    private String isComment;
    private Long categoryId;
    //访问量
    private Long viewCount;
    //标题
    private String title;
    //所属分类名
    private String categoryName;
}
