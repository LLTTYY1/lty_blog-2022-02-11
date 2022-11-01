package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/category")
public class CategoryControl {
    @Autowired
    private CategoryService categoryService;
    @SystemLog(BusinessName = "获取分类列表")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
           return categoryService.getCategoryList();
    }
}
