package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.User;
import com.LTY.enums.AppHttpCodeEnum;
import com.LTY.exception.SystemException;
import com.LTY.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
public class BlogLoginControl {
    @Autowired
    private BlogLoginService blogLoginService;
    @SystemLog(BusinessName = "登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //表示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return  blogLoginService.login(user);
    }
    @SystemLog(BusinessName = "退出登录")
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
