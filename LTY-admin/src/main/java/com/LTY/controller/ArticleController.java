package com.LTY.controller;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.vo.ArticleVo;
import com.LTY.domin.vo.PageVo;;
import com.LTY.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize,String title,String summary){
       PageVo pageVo = articleService.selectAllArticle(pageNum,pageSize,title,summary);
       return ResponseResult.okResult(pageVo);
    }
    @GetMapping("/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
      ArticleVo articleVo =  articleService.getInfo(id);
      return ResponseResult.okResult(articleVo);
    }
    @PutMapping
    public ResponseResult UpdateArticle(@RequestBody ArticleVo articleVo){
        articleService.UpdateArticle(articleVo);
        return ResponseResult.okResult();
    }
}
