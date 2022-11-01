package com.LTY.mapper;

import com.LTY.domin.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
* @createDate 2022-10-29 11:24:52
* @Entity com.LTY.domin.entity.UserRole
*/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




