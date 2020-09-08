package com.smy.shop.service;

import com.glz.pojo.RoleMenu;

import java.util.List;

public interface RoleMenuService {

    int addRoleMenu(RoleMenu roleMenu);

    int delete(long roleMenuId);

    int update(RoleMenu roleMenu);

    List<RoleMenu> selectAll(RoleMenu roleMenu);

    RoleMenu selectByRoleMenuId(long roleMenuId);
}
