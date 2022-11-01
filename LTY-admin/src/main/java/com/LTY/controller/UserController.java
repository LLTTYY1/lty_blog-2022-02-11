package com.LTY.controller;

import com.LTY.domin.ResponseResult;
import com.LTY.domin.entity.Role;
import com.LTY.domin.entity.User;
import com.LTY.domin.vo.PageVo;
import com.LTY.domin.vo.UserInfoAndRoleIdsVo;
import com.LTY.enums.AppHttpCodeEnum;
import com.LTY.exception.SystemException;
import com.LTY.service.RoleService;
import com.LTY.service.SelectAllUsers;
import com.LTY.service.UserService;
import com.LTY.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 刘泰源
 * @version 1.8
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private SelectAllUsers selectAllUsers;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    public ResponseResult list(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        PageVo pageVo = selectAllUsers.SelectAllUsers(pageNum,pageSize,userName,phonenumber,status);
        return ResponseResult.okResult(pageVo);
    }
    @PostMapping
    public ResponseResult add(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        if (!userService.checkUserNameUnique(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (!userService.checkPhoneUnique(user)){
            throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);
        }
        if (!userService.checkEmailUnique(user)){
            throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);
        }
        return userService.addUser(user);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteUser(@PathVariable(value = "id") List<Long> id){
        //不能删除当前使用的Id
        if(id.contains(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500, "不能删除当前使用的Id");
        }
        userService.removeByIds(id);
        return ResponseResult.okResult();
    }
    @GetMapping("/{id}")
    public ResponseResult updateUser(@PathVariable(value = "id") Long id){
        List<Role> roles = roleService.selectRoleAll();
        User user = userService.getById(id);
        //当前用户所具有的角色id列表
        List<Long> roleIds = roleService.selectRoleIdByUserId(id);
        UserInfoAndRoleIdsVo vo = new UserInfoAndRoleIdsVo(user,roles,roleIds);
        return ResponseResult.okResult(vo);
    }
    @PutMapping
    public ResponseResult updateUsers(@RequestBody User user){
        userService.updateUser(user);
        return ResponseResult.okResult();
    }
}