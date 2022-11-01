package com.LTY.service.impl;

import com.LTY.domin.entity.User;
import com.LTY.domin.vo.PageVo;
import com.LTY.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 刘泰源
 * @version 1.8
 */
@Service
public class SelectAllUsers extends ServiceImpl<UserMapper,User> implements com.LTY.service.SelectAllUsers {
    @Override
    public PageVo SelectAllUsers(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(StringUtils.hasText(userName),User::getUserName,userName);
        userLambdaQueryWrapper.like(StringUtils.hasText(phonenumber),User::getPhonenumber,phonenumber);
        userLambdaQueryWrapper.eq(Objects.nonNull(status),User::getStatus,status);
        Page<User> userPage = new Page<>();
        userPage.setCurrent(pageNum);
        userPage.setSize(pageSize);
        page(userPage, userLambdaQueryWrapper);
        List<User> records = userPage.getRecords();
        PageVo pageVo = new PageVo();
        pageVo.setTotal(userPage.getTotal());
        pageVo.setRows(records);
        return pageVo;
    }
}
