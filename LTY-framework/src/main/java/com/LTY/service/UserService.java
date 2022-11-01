package com.LTY.service;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 刘泰源
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2022-10-17 21:52:18
*/
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
    boolean checkUserNameUnique(String userName);

    boolean checkPhoneUnique(User user);

    boolean checkEmailUnique(User user);

    ResponseResult addUser(User user);

    void updateUser(User user);

}
