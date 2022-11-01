package com.LTY.service;

import com.LTY.domin.entity.Tag;
import com.LTY.domin.vo.PageVo;
import com.LTY.domin.vo.TagVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sg_tag(标签)】的数据库操作Service
* @createDate 2022-10-25 15:12:00
*/
public interface TagService extends IService<Tag> {

    PageVo selectAlltags(String name, Integer pageNum, Integer pageSize);

    List<TagVo> listAllTag();
}
