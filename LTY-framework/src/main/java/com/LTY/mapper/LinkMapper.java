package com.LTY.mapper;

import com.LTY.domin.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
* @author 刘泰源
* @description 针对表【sg_link(友链)】的数据库操作Mapper
* @createDate 2022-10-17 20:37:23
* @Entity com.LTY.domin.entity.Link
*/
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}




