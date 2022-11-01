package com.LTY.mapper;

import com.LTY.domin.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sg_category(分类表)】的数据库操作Mapper
* @createDate 2022-10-03 13:52:06
* @Entity com.LTY.domin.entity.SgCategory
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




