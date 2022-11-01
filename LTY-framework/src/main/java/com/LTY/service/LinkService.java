package com.LTY.service;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Link;
import com.LTY.domin.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 刘泰源
* @description 针对表【sg_link(友链)】的数据库操作Service
* @createDate 2022-10-17 20:37:23
*/
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
    PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize);
}
