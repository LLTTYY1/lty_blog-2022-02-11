package com.LTY.service;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Category;
import com.LTY.domin.vo.CategoryVo;
import com.LTY.domin.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sg_category(分类表)】的数据库操作Service
* @createDate 2022-10-03 13:52:06
*/
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    PageVo selectAllCategory(Integer pageNum, Integer pageSize, String name, String status);

    List<CategoryVo> listAllCategory();
}
