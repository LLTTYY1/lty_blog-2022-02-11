package com.LTY.service;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Article;
import com.LTY.domin.vo.ArticleVo;
import com.LTY.domin.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 刘泰源
 * @version 1.8
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    PageVo selectAllArticle(Integer pageNum, Integer pageSize, String title, String summary);

    ArticleVo getInfo(Long id);

    void UpdateArticle(ArticleVo articleVo);
}
