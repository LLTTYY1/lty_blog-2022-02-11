package com.LTY.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.LTY.domin.entity.ArticleTag;
import com.LTY.service.ArticleTagService;
import com.LTY.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
* @author 刘泰源
* @description 针对表【sg_article_tag(文章标签关联表)】的数据库操作Service实现
* @createDate 2022-10-28 17:50:00
*/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
    implements ArticleTagService{

}




