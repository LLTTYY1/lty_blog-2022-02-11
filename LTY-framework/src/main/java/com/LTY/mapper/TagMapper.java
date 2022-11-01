package com.LTY.mapper;

import com.LTY.domin.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sg_tag(标签)】的数据库操作Mapper
* @createDate 2022-10-25 15:12:00
* @Entity com.LTY.domin.entity.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




