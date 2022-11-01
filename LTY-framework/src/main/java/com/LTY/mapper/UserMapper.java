package com.LTY.mapper;

import com.LTY.domin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 刘泰源
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2022-10-17 21:53:36
* @Entity generator.domain.SysUser
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




