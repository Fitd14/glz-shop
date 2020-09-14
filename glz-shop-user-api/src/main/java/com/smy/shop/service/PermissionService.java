package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    Set<Permission> selectAll();

    ResponseResult addPermission(Permission permission);

    ResponseResult deleteById(String permissionId);

    ResponseResult update(Permission permission);

    List<Permission> selectByRoleId(String roleId);

    Permission selectById(String id);

    List<Permission> selectByPid(String pid);



}
