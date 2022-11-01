package com.LTY.service.impl;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Article;
import com.LTY.domin.entity.Category;
import com.LTY.domin.vo.CategoryVo;
import com.LTY.domin.vo.PageVo;
import com.LTY.mapper.CategoryMapper;
import com.LTY.service.ArticleService;
import com.LTY.service.CategoryService;
import com.LTY.systemContors.SysytemContors;
import com.LTY.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 刘泰源
* @description 针对表【sg_category(分类表)】的数据库操作Service实现
* @createDate 2022-10-03 13:52:06
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus,SysytemContors.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                filter(category -> SysytemContors.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public PageVo selectAllCategory(Integer pageNum, Integer pageSize, String name, String status) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.like(StringUtils.hasText(name),Category::getName,name);
        categoryLambdaQueryWrapper.eq(Objects.nonNull(status),Category::getStatus,status);
        Page<Category> categoryPage = new Page<>();
        categoryPage.setCurrent(pageNum);
        categoryPage.setSize(pageSize);
        page(categoryPage, categoryLambdaQueryWrapper);
        List<Category> records = categoryPage.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(categoryPage.getTotal());
        pageVo.setRows(records);
        return pageVo;
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getStatus, SysytemContors.NORMAL);
        List<Category> list = list(categoryLambdaQueryWrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }
}




