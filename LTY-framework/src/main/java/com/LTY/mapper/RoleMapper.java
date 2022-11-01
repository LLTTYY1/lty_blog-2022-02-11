package com.LTY.mapper;

import com.LTY.domin.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2022-10-25 18:39:08
* @Entity com.LTY.domin.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    List<Long> selectRoleIdByUserId(Long userId);
}




