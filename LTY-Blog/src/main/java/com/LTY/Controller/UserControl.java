package com.LTY.Controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.User;
import com.LTY.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/user")
public class UserControl {
    @Autowired
    private UserService userService;
    @SystemLog(BusinessName = "展示用户信息")
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
    @SystemLog(BusinessName = "更新用户信息的功能")
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    @SystemLog(BusinessName = "注册功能")
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
