package com.LTY.service;

import com.LTY.domin.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 刘泰源
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2022-10-25 18:34:02
*/
public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> selectAllMenus(Menu menu);

    boolean hasChild(Long menuId);
}
