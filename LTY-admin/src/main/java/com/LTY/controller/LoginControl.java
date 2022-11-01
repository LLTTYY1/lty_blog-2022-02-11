package com.LTY.controller;

import com.LTY.annotation.SystemLog;
import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.LoginUser;
import com.LTY.domin.entity.Menu;
import com.LTY.domin.entity.User;
import com.LTY.domin.vo.AdminUserInfoVo;
import com.LTY.domin.vo.RoutersVo;
import com.LTY.domin.vo.UserInfoVo;
import com.LTY.enums.AppHttpCodeEnum;
import com.LTY.exception.SystemException;
import com.LTY.service.LoginService;
import com.LTY.service.MenuService;
import com.LTY.service.RoleService;
import com.LTY.utils.BeanCopyUtils;
import com.LTY.utils.RedisCache;
import com.LTY.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
public class LoginControl {
    @Autowired
    private LoginService loginService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisCache redisCache;
    @SystemLog(BusinessName = "登录")
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //表示必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return  loginService.login(user);
    }
    @PostMapping("/user/logout")
    public ResponseResult logout(){
      //获取当前用户的Id
        Long userId = SecurityUtils.getUserId();
        //根据用户的Id,查找对应的token从Redis中删除
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据Id查询权限信息
        List<String> Perms =  menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据Id查询角色信息
        List<String> roles = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        AdminUserInfoVo adminUserInfo = new AdminUserInfoVo(Perms,roles,userInfoVo);
        return ResponseResult.okResult(adminUserInfo);
    }
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
