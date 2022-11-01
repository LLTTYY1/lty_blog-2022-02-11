package com.LTY.mapper;

import com.LTY.domin.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sg_article_tag(文章标签关联表)】的数据库操作Mapper
* @createDate 2022-10-28 17:50:00
* @Entity com.LTY.domin.entity.ArticleTag
*/
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}




