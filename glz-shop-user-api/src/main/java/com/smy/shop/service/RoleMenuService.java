package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    ResponseResult addRoleMenu(RoleMenu roleMenu);

    ResponseResult delete(long roleMenuId);

    ResponseResult update(RoleMenu roleMenu);

    List<RoleMenu> selectAll(RoleMenu roleMenu);

    RoleMenu selectByRoleId(long roleId);
}
