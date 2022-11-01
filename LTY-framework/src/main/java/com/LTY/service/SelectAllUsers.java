package com.LTY.service;

import com.LTY.domin.vo.PageVo;
/**
 * @author 刘泰源
 * @version 1.8
 */
public interface SelectAllUsers {
    PageVo SelectAllUsers(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);
}
