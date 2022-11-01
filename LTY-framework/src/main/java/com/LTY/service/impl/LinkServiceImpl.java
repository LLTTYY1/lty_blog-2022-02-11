package com.LTY.service.impl;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.vo.LinkVo;
import com.LTY.domin.vo.PageVo;
import com.LTY.systemContors.SysytemContors;
import com.LTY.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.LTY.domin.entity.Link;
import com.LTY.service.LinkService;
import com.LTY.mapper.LinkMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
* @author 刘泰源
* @description 针对表【sg_link(友链)】的数据库操作Service实现
* @createDate 2022-10-17 20:37:23
*/
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService{
    @Override
    public ResponseResult getAllLink() {
        //查询所有通过审核的
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Link::getStatus, SysytemContors.LINKE_STATUS_NORMAL);
        List<Link> Linklist = list(linkLambdaQueryWrapper);
        //转换成VO返回
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(Linklist, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public PageVo selectLinkPage(Link link, Integer pageNum, Integer pageSize) {
        //先设置查询条件
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //解释:当搜索框中传入的有内容时就会执行后面的匹配,若没有传入内容,就不会执行后面的,而会直接展示
        linkLambdaQueryWrapper.like(StringUtils.hasText(link.getName()),Link::getName,link.getName());
        linkLambdaQueryWrapper.eq(Objects.nonNull(link.getStatus()),Link::getStatus, link.getStatus());
        //开始进行分页查询
        Page<Link> linkPage = new Page<>();
        linkPage.setCurrent(pageNum);
        linkPage.setSize(pageSize);
        page(linkPage, linkLambdaQueryWrapper);
        //转换成Vo进行返回
        List<Link> records = linkPage.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(linkPage.getTotal());
        pageVo.setRows(records);
        return pageVo;
    }
}




