package com.LTY.service.impl;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Article;
import com.LTY.domin.entity.ArticleTag;
import com.LTY.domin.entity.Category;
import com.LTY.domin.vo.*;
import com.LTY.mapper.ArticleMapper;
import com.LTY.service.ArticleService;
import com.LTY.service.ArticleTagService;
import com.LTY.service.CategoryService;
import com.LTY.utils.BeanCopyUtils;
import com.LTY.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.LTY.systemContors.SysytemContors.ARTICLE_STATUS_NORMAL;

/**
 * @author 刘泰源
 * @version 1.8
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;
    /**
     * 查询热门文章
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        //1.必须是正式的
        articleLambdaQueryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        //2.按照浏览量进行排序
        articleLambdaQueryWrapper.orderByDesc(Article::getViewCount);
        //3.最多查出10条消息
        Page<Article> articlePage = new Page<>(1,10);
        page(articlePage, articleLambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();
        //以上查询出的数据是Artical的全部数据,实际工作中我们不需要返回那么多的数据
        //因此在下面我们就要进行vo操作,进行封装
        //通过Bean拷贝,将Article拷贝到HotArticle中
        List<HotArticleVo> hotArticleVots = new ArrayList<HotArticleVo>();
        for (Article article : records) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            hotArticleVots.add(vo);
        }
        return ResponseResult.okResult(hotArticleVots);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        //分页操作
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus, ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categorName
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据Id查询文章
        Article article = getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Long categoryId = articleDetailVo.getCategoryId();
        Category categoty = categoryService.getById(categoryId);
        if(categoty != null){
            articleDetailVo.setCategoryName(categoty.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新文章的浏览量
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    /**
     * 查询Admin中的所有文章
     * @return
     * @param pageNum
     * @param pageSize
     * @param title
     * @param summary
     */
    @Override
    public PageVo selectAllArticle(Integer pageNum, Integer pageSize, String title, String summary) {
        //思路:
        //先创建查询条件
         // 根据名称和状态进行查询
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.like(StringUtils.hasText(title),Article::getTitle,title);
        articleLambdaQueryWrapper.like(StringUtils.hasText(summary),Article::getSummary,summary);
        //进行分页
        Page<Article> articlePage = new Page<>();
        articlePage.setCurrent(pageNum);
        articlePage.setSize(pageSize);
        page(articlePage,articleLambdaQueryWrapper);
        //封装PageVo返回
        List<Article> records = articlePage.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setRows(records);
        pageVo.setTotal(articlePage.getTotal());
        return pageVo;
    }

    @Override
    public ArticleVo getInfo(Long id) {
        Article article = getById(id);
        //获取对应的标签
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId, article.getId());
        List<ArticleTag> list = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> TagList = list.stream()
                .map(articleTag -> articleTag.getTagId())
                .collect(Collectors.toList());
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        articleVo.setTags(TagList);
        return articleVo;
    }

    @Override
    public void UpdateArticle(ArticleVo articleVo) {
        Article article = BeanCopyUtils.copyBean(articleVo, Article.class);
        updateById(article);
        //删除原来的标签和博客关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加新的博客和标签关联的信息
        List<ArticleTag> collect = articleVo.getTags().stream()
                .map(tagId -> new ArticleTag(articleVo.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(collect);
    }
}
