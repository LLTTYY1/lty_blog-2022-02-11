package com.LTY.service.impl;

import com.LTY.domin.vo.PageVo;
import com.LTY.domin.vo.TagVo;
import com.LTY.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.LTY.domin.entity.Tag;
import com.LTY.service.TagService;
import com.LTY.mapper.TagMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sg_tag(标签)】的数据库操作Service实现
* @createDate 2022-10-25 15:12:00
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public PageVo selectAlltags(String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLambdaQueryWrapper.like(StringUtils.hasText(name),Tag::getName,name);
        Page<Tag> tagPage = new Page<>();
        tagPage.setCurrent(pageNum);
        tagPage.setSize(pageSize);
        page(tagPage,tagLambdaQueryWrapper);
        List<Tag> records = tagPage.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setRows(records);
        pageVo.setTotal(tagPage.getTotal());
        return pageVo;

    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list, TagVo.class);
        return tagVos;
    }
}




