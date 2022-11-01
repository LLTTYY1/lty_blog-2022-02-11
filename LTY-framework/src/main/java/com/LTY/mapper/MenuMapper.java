package com.LTY.mapper;

import com.LTY.domin.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2022-10-25 18:34:02
* @Entity com.LTY.domin.entity.Menu
*/
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> seletPermsById();

    List<Menu> selectAllMenu();

    List<Menu> selectUserMenu(Long userId);

    List<String> selectPermsByUserId(Long id);
}




