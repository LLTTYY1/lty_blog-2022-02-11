package com.LTY.service;

import com.LTY.domin.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 刘泰源
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service
* @createDate 2022-10-30 16:13:37
*/
public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}
