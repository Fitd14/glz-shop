package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;
import com.smy.shop.mapper.PermissionMapper;
import com.smy.shop.service.PermissionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Component
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    

    @Override
    public Set<Permission> selectAll() {
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<>());
        return subjectMapper(permissions);
    }

    @Override
    public ResponseResult addPermission(Permission permission) {
        permission.setId(IdUtil.simpleUUID());
        permission.setCreateTime(DateUtil.now());
        int result = permissionMapper.insert(permission);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(String permissionId) {
        int result = permissionMapper.deleteById(permissionId);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(Permission permission) {
        Permission oldPermission = permissionMapper.selectById(permission.getId());
        oldPermission.setStatus(permission.getStatus());
        oldPermission.setIcon(permission.getIcon());
        oldPermission.setName(permission.getName());
        oldPermission.setPid(permission.getPid());
        oldPermission.setSort(permission.getSort());
        oldPermission.setUri(permission.getUri());
        oldPermission.setType(permission.getType());
        int result = permissionMapper.updateById(oldPermission);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public List<Permission> selectByRoleId(String id) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("id",id));
    }

    @Override
    public Permission selectById(String id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public List<Permission> selectByPid(String pid) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("pid",pid));
    }

    private Set<Permission> subjectMapper(List<Permission> list){
        Set<Permission> permissions = new HashSet<>();
        for (Permission permission: list){
            if (permission.getPid() == 0){
                List<Permission> subjectPermission = selectByPid(permission.getId());
                HashSet<Permission> permissionHashSet = new HashSet<>(subjectPermission);
                permission.setPermissions(permissionHashSet);
                permissions.add(permission);
            }
        }
        return permissions;
    }

}
