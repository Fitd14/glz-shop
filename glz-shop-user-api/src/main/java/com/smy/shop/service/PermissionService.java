package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> selectAll();

    ResponseResult addPermission(Permission permission);

    ResponseResult deleteById(long permissionId);

    ResponseResult update(Permission permission);

    List<Permission> selectByRoleId(long roleId);

}
