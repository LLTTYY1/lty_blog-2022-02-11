package com.LTY.Controller;
import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章",description = "文章相关的接口")
public class ArticleControl {
    @Autowired
    private ArticleService articleService;

    /**
     * 文章最热列表
     * @return
     */
    @SystemLog(BusinessName = "展示所有热门文章")
    @GetMapping("/hotArticleList")
    @ApiOperation(value= "展示热门文章",notes = "获取所有的热门文章")
    public ResponseResult hotArticleList(){
           ResponseResult responseResult = articleService.hotArticleList();
           return responseResult;
    }
    @SystemLog(BusinessName = "展示所有文章")
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return  articleService.articleList(pageNum,pageSize,categoryId);
    }
    @SystemLog(BusinessName = "展示单个文章的所有内容")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable Long id){
         return articleService.updateViewCount(id);
    }
}
