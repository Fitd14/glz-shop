package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.smy.shop.mapper.RoleMapper;
import com.smy.shop.service.RoleService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Component
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ResponseResult save(Role role) {
        role.setId(IdUtil.simpleUUID());
        role.setCreateTime(DateUtil.now());
        int result = roleMapper.insert(role);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult deleteById(String id) {
        int result = roleMapper.deleteById(id);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectList(new QueryWrapper<Role>());
    }

    @Override
    public ResponseResult update(Role role) {
        Role oldRole = roleMapper.selectById(role.getId());
        oldRole.setCode(role.getCode());
        oldRole.setName(role.getName());
        oldRole.setUpdateTime(DateUtil.now());
        int result = roleMapper.updateById(oldRole);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public Role selectById(String id) {
        return roleMapper.selectById(id);
    }
}
