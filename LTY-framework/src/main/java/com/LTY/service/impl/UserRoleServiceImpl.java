package com.LTY.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.LTY.domin.entity.UserRole;
import com.LTY.service.UserRoleService;
import com.LTY.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 刘泰源
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
* @createDate 2022-10-29 11:24:52
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




