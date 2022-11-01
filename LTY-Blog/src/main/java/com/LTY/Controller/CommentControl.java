package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Comment;
import com.LTY.service.CommentService;
import com.LTY.systemContors.SysytemContors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/comment")
public class CommentControl {
    @Autowired
    private CommentService commentService;
    @SystemLog(BusinessName = "展示所有评论")
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum,Integer pageSize){
      return commentService.commentList(SysytemContors.comment_CONTXT,articleId,pageNum,pageSize);
    }
    @SystemLog(BusinessName = "写评论")
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
         return commentService.addComment(comment);
    }
    @SystemLog(BusinessName = "展示所有友链评论")
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SysytemContors.comment_LINK,null,pageNum,pageSize);
    }
}
