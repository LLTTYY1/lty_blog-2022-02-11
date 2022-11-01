package com.LTY.service;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.User;

/**
 * @author 刘泰源
 * @version 1.8
 */
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
