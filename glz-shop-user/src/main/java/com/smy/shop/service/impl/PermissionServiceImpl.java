package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;
import com.smy.shop.mapper.PermissionMapper;
import com.smy.shop.service.PermissionService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public ResponseResult addPermission(Permission permission) {
        permission.setId(IdUtil.getSnowflake(1,1).nextId());
        permission.setCreateTime(DateUtil.now());
        int result = permissionMapper.insert(permission);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(long permissionId) {
        int result = permissionMapper.deleteById(permissionId);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(Permission permission) {
        int result = permissionMapper.updateById(permission);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public List<Permission> selectByRoleId(long roleId) {
        return permissionMapper.selectList(new QueryWrapper<Permission>().eq("role_id",roleId));
    }
}
