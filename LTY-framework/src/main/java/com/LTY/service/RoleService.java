package com.LTY.service;

import com.LTY.domin.entity.Role;
import com.LTY.domin.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2022-10-25 18:39:08
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    PageVo selectAllRoles(Integer pageNum, Integer pageSize, String roleName, String status);
    List<Role> selectRoleAll();
    List<Long> selectRoleIdByUserId(Long userId);

    void insertRole(Role role);

    void updateRole(Role role);
}
