package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.RoleMenu;
import com.smy.shop.mapper.RoleMenuMapper;
import com.smy.shop.service.RoleMenuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public ResponseResult addRoleMenu(RoleMenu roleMenu) {
        int result = roleMenuMapper.insert(roleMenu);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult delete(long roleMenuId) {
        int result = roleMenuMapper.deleteById(roleMenuId);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(RoleMenu roleMenu) {
        int result = roleMenuMapper.updateById(roleMenu);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public List<RoleMenu> selectAll(RoleMenu roleMenu) {
        return roleMenuMapper.selectList(new QueryWrapper<RoleMenu>());
    }

    @Override
    public RoleMenu selectByRoleId(long roleId) {
        return roleMenuMapper.selectOne(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
    }
}
