package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.RoleMenu;

import java.util.Set;

public interface RoleMenuService {

    ResponseResult addRoleMenu(RoleMenu roleMenu);

    ResponseResult delete(String roleId,String menuId);

    ResponseResult update(RoleMenu roleMenu);

    Set<RoleMenu> selectAll();

    RoleMenu selectByRoleId(String roleId);

    RoleMenu selectByUserId(String userId);

    RoleMenu selectByMenuIdAndByRoleId(String menuId,String roleId);

}
