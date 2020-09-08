package com.smy.shop.service;

import com.glz.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> selectAll();

    int addPermission(Permission permission);

    int deleteById(long permissionId);

    int update(Permission permission);


}
