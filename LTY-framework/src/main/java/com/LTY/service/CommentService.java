package com.LTY.service;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 刘泰源
* @description 针对表【sg_comment(评论表)】的数据库操作Service
* @createDate 2022-10-19 16:15:03
*/
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
