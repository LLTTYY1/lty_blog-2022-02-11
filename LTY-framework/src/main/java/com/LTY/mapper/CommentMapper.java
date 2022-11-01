package com.LTY.mapper;

import com.LTY.domin.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sg_comment(评论表)】的数据库操作Mapper
* @createDate 2022-10-19 16:15:03
* @Entity com.LTY.domin.entity.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




